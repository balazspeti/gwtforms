package hu.banet.gwtforms.client;


import java.util.*;
import java.util.logging.*;


public abstract class CodeLOV<E> extends FormsLOV<E> {
  
  
  protected Map<String, E> keyMap;
  
  
  protected abstract String getKey(E e);  
  
  
  protected void add(E e) {
    super.add(e);
    E tmp = keyMap.put(getKey(e), e);
  }
  
  
  public CodeLOV() {
    super();
    keyMap = new HashMap<>();
  }

  
  public Set<String> getAllKeys() {
    return keyMap.keySet();
  }
  
  
  public E getByKey(String key) {
    return keyMap.get(key);
  }
  
  
}
