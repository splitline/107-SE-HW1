/*
 * Controller for the phone book application
 *   No sophisticated error handling is implemented
 */

public class PhoneBookController {

	private PhoneBookModel phonebookmodel;
	private PhoneBookView phonebookview;


	// The following are some commands that initiated by
	//   user's selection
	public static String START_COMMAND = "start";
	public static String QUIT_COMMAND = "quit";
	public static String ADD_COMMAND = "add";
	public static String SEARCH_COMMAND = "search";
	
	// A private field used to track user's input of person name
	private String name;
	
	/**
	 * Called by PhoneBookView to notify user has input
	 * @param userInput
	 */
	public void userHasInput(String userInput) {		
		String currentState = phonebookmodel.getState();

		if (userInput != null && userInput.length() != 0) {
			if (currentState.equals(PhoneBookModel.IDLE_STATE)) {
				if (userInput.equals(ADD_COMMAND)) {
					phonebookmodel.setState
					 (PhoneBookModel.ADD_NAME_STATE);
				}
				else if (userInput.equals(SEARCH_COMMAND)) {
					phonebookmodel.setState
					 (PhoneBookModel.SEARCH_STATE);
				}
				else if (userInput.equals(QUIT_COMMAND)) {
					phonebookmodel.setState
					 (PhoneBookModel.EXIT_STATE);
				}				
				else {
					phonebookmodel.setState
					 (PhoneBookModel.ERROR_STATE);
				}
			}
			else if (currentState.equals
					 (PhoneBookModel.ADD_NAME_STATE)) {
				name = userInput;
				phonebookmodel.setState
				 (PhoneBookModel.ADD_NUMBER_STATE);
			}
			else if (currentState.equals
					(PhoneBookModel.ADD_NUMBER_STATE)) {
				phonebookmodel.addAnEntry(name, userInput);
				phonebookmodel.setState(PhoneBookModel.IDLE_STATE);
			}		
			else if (currentState.equals(PhoneBookModel.SEARCH_STATE)){
				phonebookmodel.searchPhoneNumber(userInput);
				phonebookmodel.setState
				 (PhoneBookModel.SEARCH_RESULT_STATE);
			}
			else if (currentState.equals
					 (PhoneBookModel.SEARCH_RESULT_STATE) || 	
					 currentState.equals
					 (PhoneBookModel.ERROR_STATE)) {
				if (userInput.equals(START_COMMAND)) {
					phonebookmodel.setState
					 (PhoneBookModel.IDLE_STATE);
				}
				else if (userInput.equals(QUIT_COMMAND)) {
					phonebookmodel.setState
					 (PhoneBookModel.EXIT_STATE);
				}
				else {
					phonebookmodel.setState
					 (PhoneBookModel.ERROR_STATE);
				}
			}					
		}
		else {
			phonebookmodel.setState(PhoneBookModel.ERROR_STATE);
		}		
	}
		
	/**
	 * start the application
	 */
	public void start(){
		phonebookmodel.setState(PhoneBookModel.IDLE_STATE);
		while( !phonebookview.finish() ){
			phonebookview.getUserInput();
		}
	}

	/**
	 * constructor
	 */
	public PhoneBookController() {
		phonebookview = new PhoneBookView(this);
		phonebookmodel = new PhoneBookModel(phonebookview);
	}
			
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args)
	{
		PhoneBookController controller = new PhoneBookController();
		controller.start();
	}		
}
