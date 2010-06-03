CREATE TABLE strands_schedule_view (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_series                                                       varchar(12),                                                                                                                                                                                                                                                                             
	series_title                                                    varchar(150),                                                                                                                                                                                                                                                                              
	series_description                                              text,                                                                                                                                                                                                                                                                    
	bpn                                                             varchar(13),                                                                                                                                                                                                                                                                           
	program_title                                                   varchar(150),                                                                                                                                                                                                                                                                              
	program_description                                             text,                                                                                                                                                                                                                                                                            
	long_description                                                text,                                                                                                                                                                                                                                                                             
	duration_seconds                                                int,                                                                                                                                                                                                                                                                                    
	duration_hhmmss                                                 varchar(24),                                                                                                                                                                                                                                                                             
	airing_time                                                     date,                                                                                                                                                                                                                                                                               
	network                                                         char(3),                                                                                                                                                                                                                                                                                    
	v_rating                                                        varchar(4),                                                                                                                                                                                                                                                              
	weekday                                                         varchar(27),                                                                                                                                                                                                                                                                               
	is_weekend                                                      varchar(3),                                                                                                                                                                                                                                                                                
	is_repeat                                                       varchar(3),                                                                                                                                                                                                                                                                                
	is_first_air                                                    varchar(3),                                                                                                                                                                                                                                                                                
	is_captioned                                                    char(2),                                                                                                                                                                                                                                                                                    
	is_desc_video                                                   char(2),                                                                                                                                                                                                                                                                                    
	audience_discretion                                             varchar(222),                                                                                                                                                                                                                                                                              
	year_produced                                                   varchar(20),                                                                                                                                                                                                                                                                               
	episode_number                                                  int,                                                                                                                                                                                                                                                                                    
	episode_order                                                   varchar(20),                                                                                                                                                                                                                                                                               
	previous_air                                                    date,                                                                                                                                                                                                                                                                                       
	def_series_title                                                varchar(150),                                                                                                                                                                                                                                                                              
	def_series_description                                          varchar(4000),                                                                                                                                                                                                                                                                             
	def_program_title                                               varchar(150),                                                                                                                                                                                                                                                                              
	def_program_description                                         varchar(4000),                                                                                                                                                                                                                                                                             
	def_long_description                                            varchar(4000)	
);
       