package com.redsun.platf.util.convertor;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) 2010</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author Dick Pan 
 * @version 1.0
 * @since   1.0
 * <p><H3>Change history</H3></p>
 * <p>2010/10/28   : Created </p>
 *
 */
import java.util.Collection;
import java.util.LinkedHashMap;


public abstract class StringfierCollection<E> {
        protected LinkedHashMap<String, ContentStringfier<E>> collection = null;
        protected StringfierCollection() {
                collection = new LinkedHashMap<String, ContentStringfier<E>>();
                init();
        }
        protected abstract void init();

        public ContentStringfier<E> getStringfier(String key) {
                if(collection.containsKey(key)) {
                        return collection.get(key);
                }
                else {
                        return null;
                }
        }

        public LinkedHashMap<String, ContentStringfier<E>> getStringfiers(Collection<String> keySet) {
                if(keySet == null) {
                        return collection;
                }
                LinkedHashMap<String, ContentStringfier<E>> res = new LinkedHashMap<String, ContentStringfier<E>>(keySet.size());
                for(String key : keySet) {
                        assert collection.containsKey(key) :  String.format("%s 映射表中不存在关键字名称为 %s 的值", this.getClass().toString(), key);
                        ContentStringfier<E> stringfier = this.getStringfier(key);
                        if(stringfier != null){
                                res.put(key, collection.get(key));
                        }
                }
                return res;
        }

        protected void setAlias(String alias, String key) {
                assert collection.containsKey(key) : String.format("别名设置失败，%s 映射表中不存在源关键字名称为 %s 的值。", this.getClass().toString(), key);
                assert !collection.containsKey(alias) : String.format("别名设置失败，%s 映射表中已经存在目标关键字名称为 %s 的值。", this.getClass().toString(), alias);
                collection.put(alias, collection.get(key));
        }
}

