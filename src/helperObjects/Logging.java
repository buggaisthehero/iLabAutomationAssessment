package helperObjects;

import org.apache.log4j.Logger;

import enummerables.Enums.LoggingType;

public class Logging 
{
    private LoggingType logType;
    private String message;
    public final Logger logger = Logger.getLogger(this.getClass().getName());
    
    public Logging()
    {
       
    }
    
    public void printLog(LoggingType logtype, String message)
    {
        this.logType = logtype;
        this.message = message;
        
        switch(this.logType)
        {
            case ALL:
            {
                break;
            }
            case DEBUG:
            {
                logger.debug(this.message);
                System.out.println(this.message);
                break;
            }
            case ERROR:
            {
                logger.error(this.message);
                System.out.println(this.message);
                break;
            }
            case FATAL:
            {
                logger.fatal(this.message);
                System.out.println(this.message);
                break;
            }
            case INFO:
            {
                logger.info(this.message);
                System.out.println(this.message);
                break;
            }
            case TRACE:
            {
                logger.trace(this.message);
                break;
            }
            case WARN:
            {
                logger.warn(this.message);
                break;
            }
            case OFF:
			break;
			
		default:
			break;
        }

    }

}