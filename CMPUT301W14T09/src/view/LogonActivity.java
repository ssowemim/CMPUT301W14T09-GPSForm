package view;

import java.util.ArrayList;
import controller.UserController;


public class LogonActivity {

		
		/**
		 */
		public ArrayList<String> getUserNameList(){
			return null;
		}

			
			/**
			 */
			public void loadUserPreferences(java.lang.String userName){
			}


			/**
			 * @uml.property  name="userController"
			 * @uml.associationEnd  aggregation="composite" inverse="logonActivity:controller.UserController"
			 */
			private UserController userController;


			/**
			 * Getter of the property <tt>userController</tt>
			 * @return  Returns the userController.
			 * @uml.property  name="userController"
			 */
			public UserController getUserController() {
				return userController;
			}


			/**
			 * Setter of the property <tt>userController</tt>
			 * @param userController  The userController to set.
			 * @uml.property  name="userController"
			 */
			public void setUserController(UserController userController) {
				this.userController = userController;
			}

}
