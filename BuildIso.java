import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

/**
 *
 * @author ngonar
 */
public class BuildIso {
    
    public static void main(String args[]) {
        try {
            
            GenericPackager packager = new GenericPackager("cfg/packager/iso2003ascii.xml");
            ISOMsg m = new ISOMsg();
            m.setPackager(packager);
            
            m.setMTI("2100");
            m.set(48,"Latihan");
            
            m.unpack(m.pack());
            m.dump(System.out, " ");
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
