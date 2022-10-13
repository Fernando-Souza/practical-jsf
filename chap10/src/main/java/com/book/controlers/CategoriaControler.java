
package com.book.controlers;

import com.book.model.Categoria;
import com.book.service.EntityManagerService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CategoriaControler implements Serializable {
    
   private static final long serialVersionUID=1L;
   
   @Inject
    EntityManagerService<Categoria> categoriaService;   
  
   private List<Categoria> categorias;
   private List<Categoria> categoriasDeletadas;
   
   @PostConstruct
   private void init(){
       categorias =  categoriaService.findAll(Categoria.class);
       categoriasDeletadas=new ArrayList<>();
   }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> _categorias) {
        this.categorias = _categorias;
    }
   
   public String addCategoria(){
       
       categorias.add(new Categoria());
       return "";
       }
   
   public String deleteCategoria(Categoria categoria){
       
       if(categoria.getId() >=0){
        categoriasDeletadas.add(categoria);
       }
       categorias.remove(categoria);
       return "";
   }
   
   public void  deletarTudo(){
       
       categorias = new ArrayList<>();
   }
   
   public String salvar(){       
              
      for(Categoria cat:categorias){          
          categoriaService.create(cat);         
      }
      
      for(Categoria cat:categoriasDeletadas){
          categoriaService.delete(cat);
      }
      
      categoriasDeletadas =  new ArrayList<>();      
       return "";
   }
    
}
