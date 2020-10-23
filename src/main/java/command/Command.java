package command;

public abstract class Command {
	private boolean status;
	private String statusMsg;
	protected WorkingProject project;
	private String projectState;
	

	private static final String[] MESSAGES = 
	{
		"Success",
		"Class is not open",
		"Class does not exist",
		"Field does not exist",
		"Method does not exist",
		"Parameter does not exist",
		"Relationship does not exist",
		"Relationship already exists",
		"Name is already in use",
		"Name is not valid",
		"Data type is not valid",
		"Relationship type is not valid",
		"Error loading project",
		"Loaded project is not valid"
	};

	protected Command(WorkingProject project)
	{
		status = false;
		statusMsg = "Not yet executed";
		this.project = project;
	}

	public boolean getStatus()
	{
		return status;
	}

	public String getStatusMessage()
	{
		return statusMsg;
	}

	protected void updateStatus(int code)
	{
		status = (code == 0);

		if (code >= 0 && code < MESSAGES.length)
		{
			statusMsg = MESSAGES[code];
		}
		else
		{
			statusMsg = "Unknown error";
		}
	}

	public void execute()
	{
		projectState = project.toJSONString();
		onExecute();
	}

	protected abstract void onExecute();

	public void undo()
	{
		project.loadFromJSON(projectState);
	}
}
