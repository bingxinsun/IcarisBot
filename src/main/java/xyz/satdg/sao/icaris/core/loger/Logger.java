package xyz.satdg.sao.icaris.core.loger;

import net.mamoe.mirai.utils.MiraiLoggerPlatformBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * manual design ICARIS_LOGGER
 * @author GongSunink
 */
public class Logger extends MiraiLoggerPlatformBase {

    private String loggerName;
    private boolean needReDirect;
    private File redirectTo;
    private FileWriter writer;

    public Logger(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public void redirectLogToDir(File redirectTo) {
        this.redirectTo = creatLogFile(redirectTo);
        try {
            if (this.redirectTo != null) {
                this.writer = new FileWriter(this.redirectTo);
            }
            this.debug("logger is now recording with file");
        } catch (IOException e) {
            this.error(e);
        }
        this.needReDirect = true;
    }

    /**
     * 创建日志文件，提供父目录，如果文件创建成功则继续执行，失败则抛出异常
     *
     * @param file 父目录文件
     * @return log文件
     */
    private File creatLogFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                try {
                    file = new File(file.getPath() + File.separator + loggerName +
                            new SimpleDateFormat("MM-dd-hh").format(new Date()) + ".log");
                    if (file.exists()) {
                        return file;
                    }
                    if (!file.createNewFile()) {
                        throw new FileSystemException("logger file creat failed");
                    } else {
                        return file;
                    }
                } catch (IOException e) {
                    this.error(e);
                }
            }
        }else {
            try{
                if (!file.createNewFile()){
                    throw new FileSystemException("logger file creat failed");
                }else {
                    return file;
                }
            } catch (IOException e) {
                this.error(e);
            }
        }
        return null;
    }


    static class LoggerMessageBuilder {
        private String message;
        private LogType logType;

        public LoggerMessageBuilder() {
        }

        public LoggerMessageBuilder(String message) {
            this.message = message;
        }

        public LoggerMessageBuilder(String message, LogType logType) {
            this.message = message;
            this.logType = logType;
        }

        public void append(String message) {
            this.message.toCharArray()[this.message.lastIndexOf("↑")] = '↓';
            this.message += message + "↑";
        }
    }


    /**
     * log Type
     */
    enum LogType {
        /**
         * debug,verbose,error,info,warning is five conditions for log message
         */
        DEBUG,
        VERBOSE,
        ERROR,
        INFO,
        WARNING
    }

    @Override
    public void warning0(@Nullable String message, @Nullable Throwable throwable) {
        System.out.println(middleLayer(message,throwable,LogType.WARNING));
    }

    @Override
    public void debug0(@Nullable String message, @Nullable Throwable throwable){
        System.out.println(middleLayer(message,throwable,LogType.DEBUG));
    }
    @Override
    public void verbose0(@Nullable String message,@Nullable Throwable throwable){
        System.out.println(middleLayer(message,throwable,LogType.VERBOSE));
    }
    @Override
    public void error0(@Nullable String message,@Nullable Throwable throwable){
        System.out.println(middleLayer(message,throwable,LogType.ERROR));
    }
    @Override
    public void info0(@Nullable String message, @Nullable Throwable throwable){
        System.out.println(middleLayer(message,throwable,LogType.INFO));
    }

    private String middleLayer(String message,Throwable throwable,LogType logType){
        String returnMessage = formatLogMessage(message,throwable,logType);
        if (needReDirect){
            try {
                this.writer.write(returnMessage+"\n");
            }catch (IOException e){
                this.error(e);
            }
        }
        return returnMessage;
    }

    private String formatLogMessage(String message,Throwable throwable,LogType logType){
        if (message == null){
            message = "producer dose not send check-message→";
        }
        if (throwable == null){
            return String.format("=====================================================" +
                            "\n<LoggerName:%s>|<Time:%s><AffairType:%s>↓" +
                            "\n[CheckMessage:%s]↑"
                    , loggerName,new Date(),logType,message);
        }else {
            return String.format("*****************************************************" +
                            "\n<LoggerName:%s>|<Time:%s><AffairType:%s>↓" +
                            "\n[CheckMessage:%s]↓" +
                            "\n<Exception:%s>↓" +
                            "\n<ExceptionCheckMessage:%s>↓" +
                            "\n<Traces:%s>↓" +
                            "\n<CauseBy:%s>↑"
                    , loggerName, new Date(), logType, message,
                    throwable.toString(), throwable.getMessage(),
                    Arrays.toString(throwable.getStackTrace())
                    , throwable.getCause());
        }
    }

    @Nullable
    @Override
    public String getIdentity() {
        return loggerName;
    }

}