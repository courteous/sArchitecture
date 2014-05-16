package org.syncServer.ValidationAnnotations;

import javax.validation.Payload;

public class Severity  extends SeverityLevel{
	
	
	public static class Emergency extends SeverityLevel implements Payload{ public Emergency(){level = 0;}} 
	public static class Alert extends SeverityLevel  implements Payload{public Alert(){level = 1;}} 
	public static class Critical extends SeverityLevel  implements Payload{ public Critical(){level = 2;}}
	public static class Error extends SeverityLevel  implements Payload{ public Error(){level = 3;}}
	public static class Warrning extends SeverityLevel  implements Payload{public Warrning(){level = 4;}}
	public static class Notice extends SeverityLevel  implements Payload{public Notice(){level = 5;}}
	public static class Informational extends SeverityLevel  implements Payload{ public Informational(){level = 6;}}
	public static class Debug extends SeverityLevel  implements Payload{ public Debug(){level = 7;}}
	

}
