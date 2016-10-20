package io.spring.cloud.samples.commerce.ui.services.items;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableConfigurationProperties(ItemProperties.class)
public class ItemService {

    @Autowired
    ItemProperties itemproperties;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackFortune")
    public List<Item> randomItems2() {
        Item[] myListItems = restTemplate.getForObject("http://item/items", Item[].class);
        Hashtable<Long, Item> myHash = new Hashtable<Long, Item>();
        
        for( Item currItem : myListItems){
            myHash.put(currItem.getId(), currItem);
        }
        
        
        
        Map<String,String> myListPrices = restTemplate.getForObject("http://price/prices", Map.class);
        Item aux;
        for( Map.Entry<String, String> entry : myListPrices.entrySet()){
            if(myHash.containsKey(Long.valueOf(entry.getKey()))){
                aux = myHash.get(Long.valueOf(entry.getKey()));
                aux.setPrice(entry.getValue());
                myHash.put(Long.valueOf(entry.getKey()), aux);
            }
        }
        
        
        return Arrays.asList(myHash.values().toArray(new Item[0]));
        
        
    }

    private List<Item> fallbackFortune() {
        return new ArrayList();
    }
    
    @HystrixCommand(fallbackMethod = "fallbackCategory")
    public List<Item> categoryItems(String category){
        Item[] itemCategory = restTemplate.getForObject("http://item/category/"+category, Item[].class);
        Map<String,String> myListPrices = restTemplate.getForObject("http://price/prices", Map.class);
        Hashtable<Long, Item> myHash = new Hashtable<Long, Item>();
        
        for(Item currItem : itemCategory){
            myHash.put(currItem.getId(), currItem);
        }
        
        Item aux;
        for( Map.Entry<String, String> entry : myListPrices.entrySet()){
            if(myHash.containsKey(Long.valueOf(entry.getKey()))){
                aux = myHash.get(Long.valueOf(entry.getKey()));
                aux.setPrice(entry.getValue());
                myHash.put(Long.valueOf(entry.getKey()), aux);
            }
        }
        
        return Arrays.asList(myHash.values().toArray(new Item[0]));
    }
    
    private List<Item> fallbackCategory(String category){
        return new ArrayList();
    }
    
    @HystrixCommand(fallbackMethod = "fallbackItemId")
    public List<Item> itemPrice(String myId){
        Item[] items = restTemplate.getForObject("http://item/items/", Item[].class);
        Map<String,String> myListPrices = restTemplate.getForObject("http://price/prices", Map.class);
        Hashtable<Long, Item> myHash = new Hashtable<Long, Item>();
        
        for(Item currItem : items){
            myHash.put(currItem.getId(), currItem);
        }
        
        Item aux;
        for( Map.Entry<String, String> entry : myListPrices.entrySet()){
            if(myHash.containsKey(Long.valueOf(entry.getKey()))){
                aux = myHash.get(Long.valueOf(entry.getKey()));
                aux.setPrice(entry.getValue());
                myHash.put(Long.valueOf(entry.getKey()), aux);
            }
        }       
        
        Item[] resItem = new Item[1];
        resItem[0] = myHash.get(Long.valueOf(myId));
        
        return Arrays.asList(resItem);
    }
    
    private List<Item> fallbackItemId(String myId){
        return new ArrayList();
    }

    public Item Item() {
        return new Item();
    }
}