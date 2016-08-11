package cloud.igoldenbeta.hippo.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.util.ArrayList;
import java.util.List;

public class BeanAndDtoTransfer {
	/**
   * 将一个Bean(或者dto)的列表中的所有元素复制到另一哥Dto(或者bean)的列表中
   * @param one 一个已知列表
   * @param cla 要复制到的列表的类型
   * @return
   */
  public static <B, D> List<D> transOneListToAnoterList(List<B> source, Class<D> cla){
      List<D> listD = new ArrayList<D>();
      for (B b : source) {
          D d = BeanAndDtoTransfer.transOneToAnother(b, cla);
          listD.add(d);
      }
      return listD;
  }
  /**
   * 快捷复制对象到领一个对象 只能复制字段名相同的字段 底层使用copyProperties()
   * @param bean
   * @param cla
   * @return
   */
  public static <B ,D> D transOneToAnother(B source, Class<D> cla){
      try {
          D dto = cla.newInstance();
          BeanUtils.copyProperties(dto,source);
          return dto;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }
}
