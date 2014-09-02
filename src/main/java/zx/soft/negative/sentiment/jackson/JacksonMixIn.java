package zx.soft.negative.sentiment.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.Key;

/**
 * Jackson混合接口
 * 
 * @author wanggang
 *
 */
public interface JacksonMixIn {

	@JsonIgnore
	<V> Key<V> getRoot();

}
