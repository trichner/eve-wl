package ch.n1b.eve.wt.minions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
public class Mapper {
    public static  <T> List<T> mapList(List< ? extends Mappable<T>> list){
        List<T> ret;
        if(list==null){
            ret = Collections.<T>emptyList();
        }else {
            ret = new ArrayList<>();
            for (Mappable<T> item : list) {
                if (item != null) {
                    ret.add(item.map());
                }
            }
        }
        return ret;
    }

    public static  <T> T map(Mappable<T> mappable){
        if(mappable==null){
            return null;
        }else {
            return mappable.map();
        }
    }

}
