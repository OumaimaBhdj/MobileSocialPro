/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.util.Date;


/**
 *
 * @author ahmed
 */
public class Publication {
     

    Date date0 = new Date(System.currentTimeMillis());
    private  int jaime;
    private int jaimePas;
    private int id;
    private String text;
    private Date dateAj =date0  ;
     private byte[] PathJava;
    private String Path ;
    private String username;
     
  
    public Publication(int id, String text, Date dateAj) {
        this.id = id;
        this.text = text;
        this.dateAj = dateAj;
        
    }
    public Publication(String text) {
        
        this.text = text;
       
        
    }

    public Publication(String text, String Path) {
        this.text = text;
        this.Path = Path;
    }
    

    public Publication(String text,Date dateAj) {
        this.text = text;
        this.dateAj = dateAj;
      
    }
    
    public Publication(int id ,String text,Date dateAj,String Path) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
    }
 public Publication(int id ,String text,Date dateAj,String Path,int jaime,int jaimePas) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
        this.jaime=jaime;
        this.jaimePas=jaimePas;
    }
 
  public Publication(int id ,String text,Date dateAj,String Path,int jaime,int jaimePas,String PubUser) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
        this.jaime=jaime;
        this.jaimePas=jaimePas;
       
    }
  public Publication(int id ,String text,Date dateAj,String Path,int jaime,int jaimePas,String PubUser,int userId) {
         this.id=id;
        this.text = text;
        this.dateAj = dateAj;
        this.Path = Path;
        this.jaime=jaime;
        this.jaimePas=jaimePas;
       
    }
    public Publication(int id, String text) {
        this.id = id;
        this.text = text;
    }
    

   

    public Publication(int id, String text,Date dateAj, byte[] PathJava) {
        this.id = id;
        this.text = text;
        this.dateAj = dateAj;
        this.PathJava = PathJava;
    }
    

    
    public Publication() {
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateAj() {
        return dateAj;
    }

    public void setDateAj(Date dateAj) {
        this.dateAj = dateAj;
    }

   

    public byte[] getPathJava() {
        return PathJava;
    }

    public void setPathJava(byte[] PathJava) {
        this.PathJava = PathJava;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }

    public int getJaimePas() {
        return jaimePas;
    }

    public void setJaimePas(int jaimePas) {
        this.jaimePas = jaimePas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
  
   

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", text=" + text + ", dateAj=" + dateAj + '}';
    }

   
    
}
