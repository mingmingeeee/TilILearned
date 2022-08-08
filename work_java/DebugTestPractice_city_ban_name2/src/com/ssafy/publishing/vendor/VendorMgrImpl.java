package com.ssafy.publishing.vendor;

import java.util.ArrayList;
import java.util.List;

public class VendorMgrImpl implements VendorMgr {
	private List<Vendor> vendors;
	
	public VendorMgrImpl() {
		vendors=new ArrayList<Vendor>();
		
		// 도서관 생성
		vendors.add(new Library("부산시립도서관", 20000, "부산"));
		// 서점 생성
		vendors.add(new BookStore("서울책방", 5500, "101", 500, 50));
		vendors.add(new BookStore("대전책방", 2400, "201", 300, 100));
		vendors.add(new BookStore("구미책방",  1200, "301", 100, 20));
		vendors.add(new BookStore("광주책방", 1200, "401", 150, 50));
		vendors.add(new BookStore("부산책방", 1200, "501", 150, 50));
	}
	
	@Override
	public List<Vendor> search(){
		return vendors;
	}
	@Override
	public Vendor search(Vendor o){
		for(Vendor vendors : vendors) {
			if(vendors.getName().contains(o.getName())) {
				return vendors;
			}
		}
		return null;
	}
}