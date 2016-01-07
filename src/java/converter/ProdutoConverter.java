
package converter;
import Dao.ProdutoDao;
import Modelo.Produto;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;
/**
 *
 * @author MATHEUS
 */
@FacesConverter(forClass = Modelo.Produto.class)
public class ProdutoConverter implements Converter {

  @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        ProdutoDao dao = new ProdutoDao();
        int id;
 
        try {
            id = Integer.parseInt(arg2);
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Dog and select it (or use the dropdow)", "Type the name of a Dog and select it (or use the dropdow)"));
        }
 
        return dao.buscarPorId(id);
    }
 
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
 
        if (arg2 == null) {
            return "";
        }
        Produto produto = (Produto) arg2;
        return String.valueOf(produto.getId());
    }
    
}
