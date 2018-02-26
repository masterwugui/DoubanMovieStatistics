package cetc.software.lujunzizi.data.dao.implement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cetc.software.lujunzizi.data.dao.XtglyhbDao;
import cetc.software.lujunzizi.data.dataobject.PubXtglYhb;

@Component("xtglyhbDao")
public class XtglyhbDaoHibernate extends BaseHibernateDAO implements XtglyhbDao {

	@SuppressWarnings("unchecked")
	@Override
	public PubXtglYhb getXtglyhbByYhdmYhkl(String yhdm, String yhkl) {
		String sql = "SELECT * FROM pub_xtglyhb WHERE yhdm = ? AND yhkl = ?";
		Query query = getMySession().createSQLQuery(sql).addEntity(
				PubXtglYhb.class);
		query.setString(0, yhdm);
		query.setString(1, yhkl);

		List<PubXtglYhb> xtglyhbList = query.list();
		if (xtglyhbList == null) {
			xtglyhbList = new ArrayList<PubXtglYhb>();
		}
		return xtglyhbList.isEmpty() ? (null) : xtglyhbList.get(0);
	}
}
