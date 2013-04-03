dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "ee_tema"
    password = "ee_tema_"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://213.239.202.50/ee_tema?useUnicode=yes&characterEncoding=UTF-8"
        }
        hibernate {
            show_sql = true
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://213.239.202.50/ee_tema?useUnicode=yes&characterEncoding=UTF-8"
		}
		hibernate {
			show_sql = true
		}
    }
}
