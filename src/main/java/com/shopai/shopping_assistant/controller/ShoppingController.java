package com.shopai.shopping_assistant.controller;

import com.shopai.shopping_assistant.entity.*;
import com.shopai.shopping_assistant.repository.*;
import com.shopai.shopping_assistant.service.GeminiService;
import com.shopai.shopping_assistant.service.RecommendationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShoppingController {
    private final UserRepository userRepository;
    private final RecommendationService recommendationService;
    private final GeminiService geminiService;
    private final PreferenceRepository preferenceRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ShoppingController(UserRepository userRepository,RecommendationService recommendationService,GeminiService geminiService,PreferenceRepository preferenceRepository,PurchaseRepository purchaseRepository,ProductRepository productRepository,ChatMessageRepository chatMessageRepository){
        this.userRepository=userRepository;
        this.recommendationService=recommendationService;
        this.geminiService=geminiService;
        this.preferenceRepository=preferenceRepository;
        this.purchaseRepository=purchaseRepository;
        this.productRepository=productRepository;
        this.chatMessageRepository=chatMessageRepository;
    }



    @GetMapping("/home/{userId}")
    public String home(@PathVariable Long userId,Model model){
        User user=userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));
        List<Product> recommendations = recommendationService.getRecommendations(userId);
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are an intelligent AI Shopping Assistant.\n\n");
        prompt.append("Customer Details:\n");
        prompt.append("Name: ").append(user.getName()).append("\n");

        Preference preference = preferenceRepository.findByUserId(userId).orElseThrow(()->
                new RuntimeException("Preference not found"));
        prompt.append("Preferred Category: ").append(preference.getPreferredBrand()).append("\n");

        prompt.append("Budget: Rs.").append(preference.getMaxBudget()).append("\n");
        prompt.append("""
                You are an AI Shopping Assistant.
                
                Recommend the products below.
                
                Return ONLY valid HTML.
                
                Rules:
                - Use <h3> for product names.
                - Use <ul> and <li> for features.
                - Use <p> for explanations.
                - Don't return Markdown.
                - Don't return ```html.
                - Don't return plain text.
                - Make the response clean and professional.
                
                Products:
                
        """);
        for(Product product : recommendations){
            prompt.append(product.getName()).append(" - ").append(product.getDescription()).append("\n");
        }

        String aiResponse = geminiService.askGemini(prompt.toString());

        model.addAttribute("aiResponse",aiResponse);
        model.addAttribute("user",user);
        model.addAttribute("recommendations",recommendations);
        model.addAttribute("purchases",purchases);
        return "home";
    }


    @GetMapping("/assistant/{userId}")
    public String chatWithAssistant(@PathVariable Long userId,Model model){
        User user=userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));

        List<ChatMessage> chatHistory = chatMessageRepository.findByUserIdOrderByCreatedAtAsc(userId);

        model.addAttribute("user",user);
        model.addAttribute("chatHistory",chatHistory);
        return "assistant";
    }


    @PostMapping("/ask")
    public String askQuery(@RequestParam Long userId,@RequestParam String question, Model model){

        User user=userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));

        List<ChatMessage> chatHistoryToCheckFirstMessage = chatMessageRepository.findByUserIdOrderByCreatedAtAsc(userId);
        boolean firstMessage = chatMessageRepository.countByUserId(userId)==0;
        List<Product> recommendations = recommendationService.getRecommendations(userId);
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);

        ChatMessage userMessage = new ChatMessage();
        userMessage.setUser(user);
        userMessage.setRole("USER");
        userMessage.setMessage(question);
        chatMessageRepository.save(userMessage);



        StringBuilder prompt = new StringBuilder();

        if(firstMessage) {
            prompt.append("""
                        You are ShopAI.
                        This is the customer's first message.Introduce yourself briefly in a 2-3 line stating that you are ShopAI and what can u do.
                        Answer the customer's shopping question professionally.
                    
                        Customer Name:
                    """);
            prompt.append(user.getName()).append("\n");
        }
        else{
            prompt.append("""
                        You are ShopAI.
                        Continue the conversation naturally.
                        Do not Introduce yourself again.
                        Do not greet the customer again.
                        Assume this is an on-going conversation.
                        Here are your previous chats of you:
                    """);

            //add previous messages.
            prompt.append("\nConversation history:\n");
            for(ChatMessage chat : chatHistoryToCheckFirstMessage){
                if("USER".equalsIgnoreCase(chat.getRole())){
                    prompt.append("Cutomer: ").append(chat.getMessage()+"\n");
                }else{
                    prompt.append("Shop AI: ").append(chat.getMessage()+"\n");
                }
            }
            prompt.append("""        
                        Answer the customer's shopping question professionally.
                    
                        Customer Name:
                    """);
            prompt.append(user.getName()).append("\n");
        }

        prompt.append("""
                IMPORTANT:
                Recommend ONLY products from the list below.
                Never invent products.
                If no suitable product exists,You can politely says sorry no products exist and suggest the customer with any other product that slightly matches their need
                and also if still there is no product available then politely say "Sorry. We currently don't have a matching product."
                IMPORTANT: if nothing matches with what customer ask then politely say "Sorry. We currently don't have a matching product."
                IMPORTANT: if any available product matches then u should give why that product was recommended, its features compare features of available products etc..
                
                AVAILABLE PRODUCTS:
                
        """);

        for(Product product:recommendations){
            prompt.append("Name: ").append(product.getName()).append("\n");
            prompt.append("Category: ").append(product.getCategory()).append("\n");
            prompt.append("Price: ").append(product.getPrice()).append("\n");
            prompt.append("Brand: ").append(product.getBrand()).append("\n");
            prompt.append("Description: ").append(product.getDescription()).append("\n");
        }
        prompt.append("""
                Below given products are the previously purchased product of the current customer
                 you can refer this as well to give a proper answer like "Based on your Previous purchases" """);
        for(Purchase product:purchases){
            prompt.append("Name: ").append(product.getProduct().getName()).append("\n");
            prompt.append("Category: ").append(product.getProduct().getCategory()).append("\n");
            prompt.append("Price: ").append(product.getProduct().getPrice()).append("\n");
            prompt.append("Brand: ").append(product.getProduct().getBrand()).append("\n");
            prompt.append("Description: ").append(product.getProduct().getDescription()).append("\n");
        }

        prompt.append("Question: \n").append(question);
        prompt.append("""
                    IMPORTANT:
                    Do not use Markdown,**,##,* or bullet 
                    Return plain HTML using:
                    <h2>
                    <h3>
                    <ul>
                    <li>
                    <p>
                    <b>
        """);


        String answer = geminiService.askGemini(prompt.toString());

        ChatMessage aiMessage = new ChatMessage();
        aiMessage.setUser(user);
        aiMessage.setRole("AI");
        aiMessage.setMessage(answer);
        chatMessageRepository.save(aiMessage);

        List<ChatMessage> chatHistory = chatMessageRepository.findByUserIdOrderByCreatedAtAsc(userId);

        model.addAttribute("answer",answer);
        model.addAttribute("user",user);
        model.addAttribute("chatHistory",chatHistory);
        return "assistant";
    }


    @PostMapping("/buy")
    public String buyProduct(@RequestParam Long userId, @RequestParam Long productId){
        User user=userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(()->
                new RuntimeException("Product not found"));

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchaseRepository.save(purchase);

        return "redirect:/home/"+userId;
    }

    @Transactional
    @GetMapping("/assistant/exit/{userId}")
    public String exitAssistant(@PathVariable Long userId){
        chatMessageRepository.deleteByUserId(userId);
        return "redirect:/home/" +userId;
    }
}
