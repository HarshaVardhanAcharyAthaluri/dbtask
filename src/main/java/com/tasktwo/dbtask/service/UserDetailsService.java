package com.tasktwo.dbtask.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.mapping.Subclass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktwo.dbtask.annotation.LogMethodParam;
import com.tasktwo.dbtask.dto.SubClasses;
import com.tasktwo.dbtask.dto.UserDeatailInfo;
import com.tasktwo.dbtask.model.UserDetails;
import com.tasktwo.dbtask.repository.UserDetailsRepository;

@Service
public class UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);
	@Autowired
	UserDetailsRepository userRepo;
	
	@LogMethodParam
	public UserDetails getuserDetailById(Long userId) {
		log.info("{}","get user by id "+userId);
		return userRepo.findById(userId).orElseThrow(()->new RuntimeException("no User Present with "+userId));
	}
	
	public List<UserDeatailInfo> getUserDetails(){
		log.info("{}","getting all users in service");
		List<UserDeatailInfo> detailslist = new ArrayList<UserDeatailInfo>();
		
		List<UserDetails> useinfolist = (List<UserDetails>) userRepo.findAll();
		
		Map<Long,List<UserDetails>> usermap=  useinfolist.stream().filter(x->x.getParentId()!=0).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(y->y.getParentId()));
		
		useinfolist.stream().forEach(t->{
			UserDeatailInfo detailinfo = new UserDeatailInfo();
			detailinfo.setName(t.getName());
			detailslist.add(detailinfo);
			if(usermap.get(t.getId())!=null) {
				List<UserDetails> userdl  = usermap.get(t.getId());
				List<SubClasses> sublist = new ArrayList<SubClasses>();
				userdl.stream().forEach(u->{
					SubClasses subcls = new SubClasses();
					subcls.setName(u.getName());
					sublist.add(subcls);
				});
				detailinfo.setSubclasses(sublist);
			}
			
		});
		
		return detailslist;
	}
}
