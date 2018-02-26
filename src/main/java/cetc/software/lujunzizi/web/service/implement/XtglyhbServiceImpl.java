package cetc.software.lujunzizi.web.service.implement;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cetc.software.lujunzizi.data.dao.XtglyhbDao;
import cetc.software.lujunzizi.data.dataobject.PubXtglYhb;
import cetc.software.lujunzizi.web.service.XtglyhbService;
@Component("xtglyhbService")
public class XtglyhbServiceImpl implements XtglyhbService {
	private XtglyhbDao xtglyhbDao;

	public XtglyhbDao getXtglyhbDao() {
		return xtglyhbDao;
	}

	@Resource(name = "xtglyhbDao")
	public void setXtglyhbDao(XtglyhbDao xtglyhbDao) {
		this.xtglyhbDao = xtglyhbDao;
	}

	@Override
	public PubXtglYhb getXtglyhbByYhdmYhkl(String yhdm, String yhkl) {
		PubXtglYhb pubXtglYhb = xtglyhbDao.getXtglyhbByYhdmYhkl(yhdm, yhkl);
		return pubXtglYhb;
	}

}
