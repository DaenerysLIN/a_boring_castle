package wyykt.gloria.practice.castle;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();
    private ArrayList<Specialty> somthdo = new ArrayList<Specialty>();

    public Room(String description) 
    {
        this.description = description;
    }
   
    public void setExit(String dir, Room room){
    	exits.put(dir,  room);
    }
    
    public void setSomth(Specialty s){
    	somthdo.add(s);
    }
    

    
    public String getExitDesc(){
    	StringBuffer sb = new StringBuffer();
    	for(String dir : exits.keySet()){
    		sb.append(dir);
    		sb.append(" ");
    	}

    	return sb.toString();
    }
    
    public boolean hasSomethingToDo(){
    	return somthdo.size() > 0;
    }
    
    public String getSomthdoDesc(){
    	StringBuffer sb = new StringBuffer();
    	for(Specialty smth : somthdo){
    		sb.append(smth.getWhat());
    		sb.append("("+smth.getDesc()+")");
    		sb.append(" ");
    	}

    	return sb.toString();
    }
    
    
    public Room getExit(String direction){
    	return exits.get(direction);
    }
    
    public Specialty getSpecialty(String what){
    	for (Specialty s : somthdo){
    		if(s.getWhat().equals(what)){
    			return s;
    		}
    	}
    	return null;
    }
    
    
    @Override
    public String toString()
    {
        return description;
    }
    
    
}
