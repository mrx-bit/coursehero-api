create table category
(
	id bigint auto_increment
		primary key,
	is_active bit default b'1' null,
	name varchar(255) not null,
	parent_id bigint null,
	type varchar(255) not null,
	constraint FK2y94svpmqttx80mshyny85wqr
	foreign key (parent_id) references category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK2y94svpmqttx80mshyny85wqr
	on category (parent_id)
;

create table course
(
	id bigint auto_increment
		primary key,
	author_id bigint not null,
	avatar_url varchar(255) null,
	avg_rating double null,
	create_date datetime(6) null,
	dislikes_count int default '0' not null,
	full_raw_description varchar(5000) null,
	is_active bit default b'1' null,
	likes_count int default '0' not null,
	organization_id bigint null,
	price double null,
	short_raw_description varchar(500) not null,
	title varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKk7lpx5vpabikdmqwx0htpbo9b
	on course (organization_id)
;

create index FKmaatvkqyrdpwoiq3opi1obk7b
	on course (author_id)
;

create table course_category
(
	course_id bigint not null,
	category_id bigint not null,
	constraint FK1wqc8fcw0dbd1xa602bsov2he
	foreign key (category_id) references category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK1wqc8fcw0dbd1xa602bsov2he
	on course_category (category_id)
;

create index FKbux6caeld4c27c84h1hro1wik
	on course_category (course_id)
;

create table course_feedback
(
	id bigint auto_increment
		primary key,
	course_id bigint not null,
	create_date datetime(6) null,
	is_active bit default b'1' null,
	text varchar(255) null,
	type varchar(255) null,
	user_id bigint not null,
	constraint FKky5c70g5hmfq057l30r96mjb
	foreign key (course_id) references course (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKe9bo7khjiraynibeylksxi2uo
	on course_feedback (user_id)
;

create index FKky5c70g5hmfq057l30r96mjb
	on course_feedback (course_id)
;

create table course_tag
(
	course_id bigint not null,
	tag_id bigint not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK47fbjgitnu2wmheeji4801n8v
	on course_tag (course_id)
;

create index FKj7piuv0dh0v01l3aolwwd1jwh
	on course_tag (tag_id)
;

create table disability
(
	id bigint auto_increment
		primary key,
	is_active bit default b'1' null,
	name varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table event
(
	id bigint auto_increment
		primary key,
	author_id bigint not null,
	avatar_url varchar(255) null,
	avg_rating double null,
	create_date datetime(6) null,
	dislikes_count int default '0' not null,
	end_date datetime(6) null,
	full_raw_description varchar(5000) null,
	is_active bit default b'1' null,
	likes_count int default '0' not null,
	organization_id bigint null,
	price double null,
	short_raw_description varchar(500) null,
	start_date datetime(6) null,
	title varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKcs4p0te0qihtbanodmdqqkwtl
	on event (author_id)
;

create index FKkarqc3c84scr3r5ncv5stqbk2
	on event (organization_id)
;

create table event_feedback
(
	id bigint auto_increment
		primary key,
	create_date datetime(6) null,
	event_id bigint not null,
	is_active bit default b'1' null,
	text varchar(255) null,
	type varchar(255) null,
	user_id bigint not null,
	constraint FKsdksrlbnomwrt89c67tpi7ur9
	foreign key (event_id) references event (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKr2h6yqyv56wrfvsdd26bi2ptc
	on event_feedback (user_id)
;

create index FKsdksrlbnomwrt89c67tpi7ur9
	on event_feedback (event_id)
;

create table interest
(
	id bigint auto_increment
		primary key,
	is_active bit default b'1' null,
	name varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table lesson
(
	id bigint auto_increment
		primary key,
	avatar_url varchar(255) null,
	avg_rating double null,
	course_id bigint not null,
	dislikes_count int default '0' not null,
	is_active bit default b'1' null,
	likes_count int default '0' not null,
	order_val int not null,
	raw_text varchar(5000) null,
	title varchar(255) not null,
	constraint FKjs3c7skmg8bvdddok5lc7s807
	foreign key (course_id) references course (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKjs3c7skmg8bvdddok5lc7s807
	on lesson (course_id)
;

create table lesson_assignment
(
	id bigint auto_increment
		primary key,
	create_date datetime(6) null,
	is_active bit default b'1' null,
	lesson_id bigint not null,
	order_val int not null,
	raw_text varchar(255) null,
	type varchar(255) null,
	user_id bigint null,
	constraint FK7r0ghhw4xnlk6uh47viicbxii
	foreign key (lesson_id) references lesson (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK7r0ghhw4xnlk6uh47viicbxii
	on lesson_assignment (lesson_id)
;

create index FKa207wdhhv2mk9cu2ok9ob1hto
	on lesson_assignment (user_id)
;

create table lesson_feedback
(
	id bigint auto_increment
		primary key,
	create_date datetime(6) null,
	is_active bit default b'1' null,
	lesson_id bigint null,
	text varchar(255) null,
	type varchar(255) null,
	user_id bigint null,
	constraint FKr3ifpbu2ylkwpjj5119x6d4au
	foreign key (lesson_id) references lesson (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKpjr8nl57o3faypfudrghae7nw
	on lesson_feedback (user_id)
;

create index FKr3ifpbu2ylkwpjj5119x6d4au
	on lesson_feedback (lesson_id)
;

create table news
(
	id bigint auto_increment
		primary key,
	author_id bigint not null,
	avatar_url varchar(255) null,
	create_date datetime(6) null,
	full_raw_description varchar(5000) null,
	is_active bit default b'1' null,
	organization_id bigint null,
	short_raw_description varchar(500) null,
	title varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK4sf3nciqc4x8fc5xwo647o6ha
	on news (organization_id)
;

create index FKrxsvhjf5khl0mf6jf3au0vqaf
	on news (author_id)
;

alter table course_category
	add constraint FKbux6caeld4c27c84h1hro1wik
foreign key (course_id) references news (id)
;

alter table course_tag
	add constraint FK47fbjgitnu2wmheeji4801n8v
foreign key (course_id) references news (id)
;

create table news_feedback
(
	id bigint auto_increment
		primary key,
	create_date datetime(6) null,
	is_active bit default b'1' null,
	news_id bigint not null,
	text varchar(255) null,
	type varchar(255) null,
	user_id bigint not null,
	constraint FKicmyliq422r5esb8rbn7x6fp7
	foreign key (news_id) references news (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKicmyliq422r5esb8rbn7x6fp7
	on news_feedback (news_id)
;

create index FKry38ykks6ntu503buq92t2sv2
	on news_feedback (user_id)
;

create table organization
(
	id bigint auto_increment
		primary key,
	address varchar(255) null,
	avatar_url varchar(255) null,
	is_active bit default b'1' null,
	name varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

alter table course
	add constraint FKk7lpx5vpabikdmqwx0htpbo9b
foreign key (organization_id) references organization (id)
;

alter table event
	add constraint FKkarqc3c84scr3r5ncv5stqbk2
foreign key (organization_id) references organization (id)
;

alter table news
	add constraint FK4sf3nciqc4x8fc5xwo647o6ha
foreign key (organization_id) references organization (id)
;

create table pathology
(
	id bigint auto_increment
		primary key,
	is_active bit default b'1' null,
	name varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table poll
(
	id bigint auto_increment
		primary key,
	is_active bit default b'1' null,
	order_val int not null,
	text varchar(255) not null,
	type varchar(255) null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table poll_option
(
	id bigint auto_increment
		primary key,
	is_active bit default b'1' null,
	poll_id bigint not null,
	text varchar(255) null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table role
(
	id bigint auto_increment
		primary key,
	name varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table session
(
	id bigint auto_increment
		primary key,
	closed datetime(6) null,
	context varchar(255) null,
	created datetime(6) null,
	email varchar(255) not null,
	locale varchar(255) null,
	state_id bigint null,
	session_token varchar(255) not null,
	updated datetime(6) null,
	user_id bigint not null,
	constraint UK_g5buif0j7oavm63jck6bg9p0i
	unique (email),
	constraint UK_d16yqd2ghhaxopy9mvwqsnu08
	unique (session_token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK1bi1pmqjgipw7dx3j6bl37dja
	on session (user_id)
;

create index FKqkr6oeqtwhesnhumajbxhe7gm
	on session (state_id)
;

create table session_state
(
	id bigint auto_increment
		primary key,
	name varchar(255) null,
	constraint UK881yvkotd3hyvkrts7vvb5ym6
	unique (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

alter table session
	add constraint FKqkr6oeqtwhesnhumajbxhe7gm
foreign key (state_id) references session_state (id)
;

create table tag
(
	id bigint auto_increment
		primary key,
	is_active bit default b'1' null,
	name varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

alter table course_tag
	add constraint FKj7piuv0dh0v01l3aolwwd1jwh
foreign key (tag_id) references tag (id)
;

create table user
(
	id bigint auto_increment
		primary key,
	birthdate date null,
	email varchar(255) not null,
	enabled bit null,
	name varchar(255) null,
	parent_id bigint null,
	password varchar(255) null,
	reg_date datetime(6) null,
	role_id bigint null,
	constraint UK_ob8kqyqqgmefl0aco34akdtpe
	unique (email),
	constraint FK4k8a1qa0wofm01aijepmu0d4g
	foreign key (parent_id) references user (id),
	constraint FKn82ha3ccdebhokx3a8fgdqeyy
	foreign key (role_id) references role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK4k8a1qa0wofm01aijepmu0d4g
	on user (parent_id)
;

create index FKn82ha3ccdebhokx3a8fgdqeyy
	on user (role_id)
;

alter table course
	add constraint FKmaatvkqyrdpwoiq3opi1obk7b
foreign key (author_id) references user (id)
;

alter table course_feedback
	add constraint FKe9bo7khjiraynibeylksxi2uo
foreign key (user_id) references user (id)
;

alter table event
	add constraint FKcs4p0te0qihtbanodmdqqkwtl
foreign key (author_id) references user (id)
;

alter table event_feedback
	add constraint FKr2h6yqyv56wrfvsdd26bi2ptc
foreign key (user_id) references user (id)
;

alter table lesson_assignment
	add constraint FKa207wdhhv2mk9cu2ok9ob1hto
foreign key (user_id) references user (id)
;

alter table lesson_feedback
	add constraint FKpjr8nl57o3faypfudrghae7nw
foreign key (user_id) references user (id)
;

alter table news
	add constraint FKrxsvhjf5khl0mf6jf3au0vqaf
foreign key (author_id) references user (id)
;

alter table news_feedback
	add constraint FKry38ykks6ntu503buq92t2sv2
foreign key (user_id) references user (id)
;

alter table session
	add constraint FK1bi1pmqjgipw7dx3j6bl37dja
foreign key (user_id) references user (id)
;

create table user_assignment_answer
(
	id bigint auto_increment
		primary key,
	assignment_id bigint null,
	create_date datetime(6) null,
	text_raw_answer varchar(255) null,
	user_id bigint not null,
	constraint FKm54lwaxv6d342bcm5879d74tt
	foreign key (assignment_id) references lesson_assignment (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKm54lwaxv6d342bcm5879d74tt
	on user_assignment_answer (assignment_id)
;

create table user_course
(
	user_id bigint not null,
	course_id bigint not null,
	constraint FKpv8tt3252hb6kyej8p7e7pokl
	foreign key (user_id) references user (id),
	constraint FKka18m18kpimodvy8yg2icu083
	foreign key (course_id) references course (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKka18m18kpimodvy8yg2icu083
	on user_course (course_id)
;

create index FKpv8tt3252hb6kyej8p7e7pokl
	on user_course (user_id)
;

create table user_disability
(
	user_id bigint not null,
	disability_id bigint not null,
	constraint FKmweo4m1wyy6v73a3kum2evg4b
	foreign key (user_id) references user (id),
	constraint FKfkup0b9b1fccwbk85gcfwej8p
	foreign key (disability_id) references disability (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKfkup0b9b1fccwbk85gcfwej8p
	on user_disability (disability_id)
;

create index FKmweo4m1wyy6v73a3kum2evg4b
	on user_disability (user_id)
;

create table user_document_attachment
(
	id bigint auto_increment
		primary key,
	content mediumblob null,
	deleted bit null,
	deleted_date datetime(6) null,
	filename varchar(255) null,
	mimetype varchar(255) null,
	type varchar(255) null,
	uploaded datetime(6) null,
	user_assignment_answer_id bigint null,
	user_id bigint not null,
	constraint FKn3ayiry06e3ie7eb56q0qeqnt
	foreign key (user_assignment_answer_id) references user_assignment_answer (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKn3ayiry06e3ie7eb56q0qeqnt
	on user_document_attachment (user_assignment_answer_id)
;

create table user_interest
(
	user_id bigint not null,
	interest_id bigint not null,
	constraint FKdi9smphhv09dottb2sc1j3k64
	foreign key (user_id) references user (id),
	constraint FKb2c20k2dqknrm5t337typ3s1b
	foreign key (interest_id) references interest (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FKb2c20k2dqknrm5t337typ3s1b
	on user_interest (interest_id)
;

create index FKdi9smphhv09dottb2sc1j3k64
	on user_interest (user_id)
;

create table user_pathology
(
	user_id bigint not null,
	pathology_id bigint not null,
	constraint FKkrd63pesrk0frmoxij3hr3a1s
	foreign key (user_id) references user (id),
	constraint FK7xnhshu3f1xqyjylyox7nfmba
	foreign key (pathology_id) references pathology (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK7xnhshu3f1xqyjylyox7nfmba
	on user_pathology (pathology_id)
;

create index FKkrd63pesrk0frmoxij3hr3a1s
	on user_pathology (user_id)
;

create table user_poll_answer
(
	id bigint auto_increment
		primary key,
	create_date datetime(6) null,
	poll_id bigint not null,
	poll_option_id bigint not null,
	text_answer varchar(255) null,
	user_id bigint null,
	constraint UK2q18b5i3o4chcshmhonv51o36
	unique (user_id, poll_id),
	constraint FK50vlt5jybeb1ale8fy2nh2d7l
	foreign key (poll_id) references poll (id),
	constraint FK3bq3c89o2pe21t1d3tv0vyhpm
	foreign key (poll_option_id) references poll_option (id),
	constraint FK6qx6t375bh4e1plb9ipwnrqjg
	foreign key (user_id) references user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create index FK3bq3c89o2pe21t1d3tv0vyhpm
	on user_poll_answer (poll_option_id)
;

create index FK50vlt5jybeb1ale8fy2nh2d7l
	on user_poll_answer (poll_id)
;

