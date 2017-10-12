package edu.mum.se.poseidon.web;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.security.core.context.SecurityContext;

public class AdminNavigationManager {
	private static AdminNavigationManager _instance = new AdminNavigationManager();
	
	public class NavigationLink {
		public String LinkText;
		public String ActionName;
		public String ControllerName;
		public String NavigationId;
		public Predicate<SecurityContext> CheckAuthorisation;
	}

	public class NavigationCategory {
		public String Icon;
		public String Category;
		public List<NavigationLink> Links;

		public boolean HasPageId(String pageId) {
			return pageId != null && this.Links.stream().anyMatch(x -> x.NavigationId == pageId);
		}
	}

	static List<NavigationCategory> Categories;
	static List<NavigationLink> Links;
	
	private AdminNavigationManager() {
		Links = new ArrayList<>();
		NavigationLink block = new NavigationLink();
		
		block.ControllerName = "Block";
		block.ActionName = "Index";
		block.LinkText = "BlockLink";
		block.CheckAuthorisation = x -> x.getAuthentication().getAuthorities()
				.stream().map(y -> y.getAuthority()).anyMatch(z -> z.equals("ADMIN"));
		
		Links.add(block);
	}

	public static AdminNavigationManager getInstance() {
		return _instance;
	}
	private void test() {
		new NavigationLink().CheckAuthorisation = x -> x.getAuthentication().getAuthorities().stream()
				.anyMatch(y -> y.equals("ADMIN"));
	}
}
