
import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.XMLPackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;


public class TestServer {
 
    public static void main(String a[]) throws ISOException, IOException{
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ServerChannel channel = new ASCIIChannel(new ISO87APackager());
        ((LogSource)channel).setLogger(logger, "Channel");
        
        ISOServer server = new ISOServer(8000, channel, null);
        server.setLogger(logger, "Server");
        server.addISORequestListener(new TestProcess());
        new Thread(server).start();
    }
    
    static class TestProcess implements ISORequestListener {

        @Override
        public boolean process(ISOSource source, ISOMsg m) {
            
            try {
                m.setResponseMTI();
                m.set(39,"00");
                source.send(m);
            }
            catch(ISOException e1){
                e1.printStackTrace();
            }
            catch(IOException e2){
                e2.printStackTrace();
            }
            
            return true;
        }
        
    }
    
}
