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

import se.kth.mobdev.ruontime.persistence.model.User;
import se.kth.mobdev.ruontime.service.ServiceFactory;

/**
 * @author Jasper
 *
 */
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {
		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		    Object ret = null;
		    ret = ServiceFactory.getUserAuthService().getUser(arg2);
		    return ret;
		}

		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		    String str = "";
		    if (arg2 instanceof User) {
		        str = "" + ((User) arg2).getUserName();
		    }
		    return str;
		}
}
