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

import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.Group;

/**
 * @author Jasper
 *
 */
@FacesConverter(value = "groupConverter")
public class GroupConverter implements Converter {
		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		    Object ret = null;
		    ret = PersistenceFactory.getGroupDao().findByName(arg2);
		    System.out.println("converted " + arg2 + " to " + ret);
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
