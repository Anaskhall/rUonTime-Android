/**
 * 
 */
package se.kth.mobdev.ruontime.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import se.kth.mobdev.ruontime.model.Group;

/**
 * @author Jasper
 *
 */
@FacesConverter(value = "groupConverter")
public class GroupConverter implements Converter {
		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		    Object ret = null;
		    if (arg1 instanceof PickList) {
		        Object dualList = ((PickList) arg1).getValue();
		        DualListModel dl = (DualListModel) dualList;
		        for (Object o : dl.getSource()) {
		            String id = "" + ((Group) o).getName();
		            if (arg2.equals(id)) {
		                ret = o;
		                break;
		            }
		        }
		        if (ret == null)
		            for (Object o : dl.getTarget()) {
		                String id = "" + ((Group) o).getName();
		                if (arg2.equals(id)) {
		                    ret = o;
		                    break;
		                }
		            }
		    }
		    return ret;
		}

		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		    String str = "";
		    if (arg2 instanceof Group) {
		        str = "" + ((Group) arg2).getName();
		    }
		    return str;
		}
}
