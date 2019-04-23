package enummerables;

public class Enums 
{
    public enum LoggingType 
    {
        ALL, DEBUG, ERROR, FATAL, INFO, OFF, TRACE, WARN
    }

    public enum LocatorType
    {
        ID, NAME, XPATH, PARTIAL_LINK_TEXT, LINK_TEXT, CSS, CLASS_NAME 
    }  

    public enum ResultStatus
    {
        PASS, FAIL, WARNING, UNCERTAIN
    }

    public enum BrowserTypes
    { 
    	Chrome(3,new String[]{"58"}),
        Edge (1,new String[]{"14"});
        

        private final int position;
        private final String[] browserVersions;


        BrowserTypes(int pos, String[] ver)
        {
            this.position = pos;
            this.browserVersions = ver;
        }

        public int getPosition() {
            return position;
        }

        public String[] getBrowserVersions() {
            return browserVersions;
        }
    }
    
    public enum Platforms 
    {
        Windows (0,new String[]{"7"}), 
        OS_X (1,new String[]{"Sierra"});
        
        private final int position;
        private final String[] platformTypes;

        Platforms(int pos, String[] ver)
        {
            this.position = pos;
            this.platformTypes = ver;
        }

        public int getPosition() {
            return position;
        }

        public String[] getPlaformVersions() {
            return platformTypes;
        }
    }

    public enum Environment
    {
        Dev("Dev URL"),
        QA("QA URL"),
        Prod("https://www.ilabquality.com/");
        
        public final String urlUnderTest;


        Environment(String environmentURL)
        {
            this.urlUnderTest = environmentURL;
        }
 
    }
}