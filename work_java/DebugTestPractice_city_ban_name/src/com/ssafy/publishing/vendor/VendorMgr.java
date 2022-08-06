package com.ssafy.publishing.vendor;

import java.util.List;

public interface VendorMgr {
	List<Vendor> search();
	Vendor search(Vendor o);
}