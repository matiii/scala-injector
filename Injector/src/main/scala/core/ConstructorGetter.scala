package core

import java.lang.reflect.Constructor

/**
 * Created by mmazur01 on 20-10-2015.
 */
//TODO to finish
trait ConstructorGetter {

  protected def getCtr(source: Class[_]): Constructor[_] = {
    source.getConstructors()(0)
  }

}