package controller;

import java.util.ArrayList;

import model.Comment;
import model.User;
import view.LogonActivity;
import view.EditCommentActivity;

/**
 * @uml.dependency   supplier="controller.IController"
 * @uml.dependency   supplier="view.LogonActivity"
 */
public class UserController {

		
		/**
		 */
		public ArrayList<String> getUserNames(){
			return null;
		}

			
			/**
			 */
			public void loadUserPreferences(){
			}


				
				/**
				 */
				public void generateUnreadMarkers(){
				}


					
					/**
					 */
					public void FavoriteComment(Comment comment){
					}


						
						/**
						 */
						public void pollUserLocation(){
						}



						/** 
						 * @uml.property name="user"
						 * @uml.associationEnd aggregation="composite" inverse="userController:model.User"
						 */
						private User user;



						/** 
						 * Getter of the property <tt>user</tt>
						 * @return  Returns the user.
						 * @uml.property  name="user"
						 */
						public User getUser() {
							return user;
						}


						/** 
						 * Setter of the property <tt>user</tt>
						 * @param user  The user to set.
						 * @uml.property  name="user"
						 */
						public void setUser(User user) {
							this.user = user;
						}



						/**
						 * @uml.property  name="logonActivity"
						 * @uml.associationEnd  inverse="userController:view.LogonActivity"
						 */
						private LogonActivity logonActivity;



						/**
						 * Getter of the property <tt>logonActivity</tt>
						 * @return  Returns the logonActivity.
						 * @uml.property  name="logonActivity"
						 */
						public LogonActivity getLogonActivity() {
							return logonActivity;
						}


						/**
						 * Setter of the property <tt>logonActivity</tt>
						 * @param logonActivity  The logonActivity to set.
						 * @uml.property  name="logonActivity"
						 */
						public void setLogonActivity(LogonActivity logonActivity) {
							this.logonActivity = logonActivity;
						}



						/**
						 * @uml.property  name="editCommentActivity"
						 * @uml.associationEnd  aggregation="composite" inverse="userController:view.EditCommentActivity"
						 */
						private EditCommentActivity editCommentActivity;



						/**
						 * Getter of the property <tt>editCommentActivity</tt>
						 * @return  Returns the editCommentActivity.
						 * @uml.property  name="editCommentActivity"
						 */
						public EditCommentActivity getEditCommentActivity() {
							return editCommentActivity;
						}


						/**
						 * Setter of the property <tt>editCommentActivity</tt>
						 * @param editCommentActivity  The editCommentActivity to set.
						 * @uml.property  name="editCommentActivity"
						 */
						public void setEditCommentActivity(
								EditCommentActivity editCommentActivity) {
									this.editCommentActivity = editCommentActivity;
								}



						/**
						 * @uml.property  name="editCommentActivity1"
						 * @uml.associationEnd  aggregation="composite" inverse="userController1:view.EditCommentActivity"
						 */
						private EditCommentActivity editCommentActivity1;



						/**
						 * Getter of the property <tt>editCommentActivity1</tt>
						 * @return  Returns the editCommentActivity1.
						 * @uml.property  name="editCommentActivity1"
						 */
						public EditCommentActivity getEditCommentActivity1() {
							return editCommentActivity1;
						}


						/**
						 * Setter of the property <tt>editCommentActivity1</tt>
						 * @param editCommentActivity1  The editCommentActivity1 to set.
						 * @uml.property  name="editCommentActivity1"
						 */
						public void setEditCommentActivity1(
								EditCommentActivity editCommentActivity1) {
									this.editCommentActivity1 = editCommentActivity1;
								}



						/**
						 * @uml.property  name="editCommentActivity2"
						 * @uml.associationEnd  aggregation="composite" inverse="userController2:view.EditCommentActivity"
						 */
						private EditCommentActivity editCommentActivity2;



						/**
						 * Getter of the property <tt>editCommentActivity2</tt>
						 * @return  Returns the editCommentActivity2.
						 * @uml.property  name="editCommentActivity2"
						 */
						public EditCommentActivity getEditCommentActivity2() {
							return editCommentActivity2;
						}


						/**
						 * Setter of the property <tt>editCommentActivity2</tt>
						 * @param editCommentActivity2  The editCommentActivity2 to set.
						 * @uml.property  name="editCommentActivity2"
						 */
						public void setEditCommentActivity2(
								EditCommentActivity editCommentActivity2) {
							this.editCommentActivity2 = editCommentActivity2;
						}

}
