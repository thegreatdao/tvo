SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `tvo_web_repository` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

-- -----------------------------------------------------
-- Table `tvo_web_repository`.`geo_filter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`geo_filter` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`geo_filter` (
  `geo_filter_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `filter_name` VARCHAR(50) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`geo_filter_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_root`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_root` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_root` (
  `asset_root_id` INT NOT NULL AUTO_INCREMENT ,
  `telescope_asset_id` VARCHAR(255) NULL ,
  `telescope_record_id` INT NULL ,
  `title` VARCHAR(255) NOT NULL ,
  `source` VARCHAR(100) NOT NULL ,
  `asset_type` VARCHAR(60) NULL ,
  `description_internet` TEXT NULL ,
  `description_short` VARCHAR(255) NULL ,
  `age_rating` CHAR(2) NULL ,
  `user_time_start` DATETIME NOT NULL ,
  `user_time_end` DATETIME NOT NULL ,
  `duration` TIME NULL ,
  `geo_filter_id` INT NULL ,
  `release_date` DATETIME NOT NULL ,
  `created_on` DATETIME NOT NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  INDEX `fk_geo_filter_id` (`geo_filter_id` ASC) ,
  PRIMARY KEY (`asset_root_id`) ,
  CONSTRAINT `fk_geo_filter_id`
    FOREIGN KEY (`geo_filter_id` )
    REFERENCES `tvo_web_repository`.`geo_filter` (`geo_filter_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_program`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_program` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_program` (
  `asset_program_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  PRIMARY KEY (`asset_program_id`) ,
  INDEX `fk_asset_program_root` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_asset_program_root`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_video`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_video` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_video` (
  `asset_video_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `asset_program_id` INT NULL ,
  `length` INT NULL ,
  `link_url` TEXT NULL ,
  `link_title` VARCHAR(255) NULL ,
  `master_series_number` VARCHAR(12) NULL ,
  `is_embed_code` BIT NULL ,
  `thumbnail_url` TEXT NULL ,
  `video_still_url` TEXT NULL ,
  `video_url` TEXT NULL ,
  `bc_ref_id` VARCHAR(255) NULL ,
  PRIMARY KEY (`asset_video_id`) ,
  INDEX `fk_asset_video` (`asset_root_id` ASC) ,
  INDEX `fk_asset_program` (`asset_program_id` ASC) ,
  CONSTRAINT `fk_asset_video`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_asset_program`
    FOREIGN KEY (`asset_program_id` )
    REFERENCES `tvo_web_repository`.`asset_program` (`asset_program_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_article` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_article` (
  `asset_article_id` INT NOT NULL ,
  `asset_root_id` INT NULL ,
  `teaser` VARCHAR(255) NULL ,
  `body` TEXT NULL ,
  PRIMARY KEY (`asset_article_id`) ,
  INDEX `fk_asset_article_id` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_asset_article_id`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_relation` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_relation` (
  `asset_relation_id` INT NOT NULL ,
  `asset_parent_id` INT NULL ,
  `sort_order` INT NULL ,
  `asset_child_id` INT NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`asset_relation_id`) ,
  INDEX `fk_asset_parent` (`asset_parent_id` ASC) ,
  INDEX `fk_asset_child` (`asset_child_id` ASC) ,
  CONSTRAINT `fk_asset_parent`
    FOREIGN KEY (`asset_parent_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_asset_child`
    FOREIGN KEY (`asset_child_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_attachment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_attachment` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_attachment` (
  `asset_attachment_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `name` VARCHAR(255) NULL ,
  `mime_type` VARCHAR(100) NULL ,
  `url_location` TEXT NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`asset_attachment_id`) ,
  INDEX `fk_asset_attachment_id` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_asset_attachment_id`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`countries_iso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`countries_iso` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`countries_iso` (
  `iso2` CHAR(2) NOT NULL ,
  `iso3` CHAR(3) NULL ,
  `print_name` VARCHAR(80) NULL ,
  `numcode` SMALLINT NULL ,
  PRIMARY KEY (`iso2`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`geo_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`geo_location` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`geo_location` (
  `geo_location_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `country_iso2` CHAR(2) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`geo_location_id`) ,
  INDEX `fk_country_iso` (`country_iso2` ASC) ,
  INDEX `fk_geo_asset` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_country_iso`
    FOREIGN KEY (`country_iso2` )
    REFERENCES `tvo_web_repository`.`countries_iso` (`iso2` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_geo_asset`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_blog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_blog` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_blog` (
  `asset_blog_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `body` TEXT NULL ,
  `rss_feed_name` TEXT NULL ,
  PRIMARY KEY (`asset_blog_id`) ,
  INDEX `fk_asset_blog` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_asset_blog`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`domain_name`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`domain_name` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`domain_name` (
  `domain_name_id` INT NOT NULL AUTO_INCREMENT ,
  `domain_name` VARCHAR(200) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`domain_name_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`domain_publish`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`domain_publish` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`domain_publish` (
  `domain_publish_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `domain_name_id` INT NULL ,
  `is_published` BIT NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`domain_publish_id`) ,
  INDEX `fk_domain_publish` (`asset_root_id` ASC) ,
  INDEX `fk_domain_name` (`domain_name_id` ASC) ,
  CONSTRAINT `fk_domain_publish`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_domain_name`
    FOREIGN KEY (`domain_name_id` )
    REFERENCES `tvo_web_repository`.`domain_name` (`domain_name_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`brightcove_id`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`brightcove_id` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`brightcove_id` (
  `brightcove_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_video_id` INT NULL ,
  `domain_name_id` INT NULL ,
  `bc_id` VARCHAR(255) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`brightcove_id`) ,
  INDEX `fk_bc_video` (`asset_video_id` ASC) ,
  INDEX `fk_bc_domain_name` (`domain_name_id` ASC) ,
  CONSTRAINT `fk_bc_video`
    FOREIGN KEY (`asset_video_id` )
    REFERENCES `tvo_web_repository`.`asset_video` (`asset_video_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bc_domain_name`
    FOREIGN KEY (`domain_name_id` )
    REFERENCES `tvo_web_repository`.`domain_name` (`domain_name_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`category` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`category` (
  `category_id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`category_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`category_sub`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`category_sub` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`category_sub` (
  `category_sub_id` INT NOT NULL AUTO_INCREMENT ,
  `category_id` INT NULL ,
  `name` VARCHAR(100) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`category_sub_id`) ,
  INDEX `fk_category` (`category_id` ASC) ,
  CONSTRAINT `fk_category`
    FOREIGN KEY (`category_id` )
    REFERENCES `tvo_web_repository`.`category` (`category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_metadata`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_metadata` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_metadata` (
  `asset_metadata_id` INT NOT NULL AUTO_INCREMENT ,
  `category_sub_id` INT NULL ,
  `asset_root_id` INT NULL ,
  `domain_name_id` INT NULL ,
  `title` VARCHAR(255) NULL ,
  `description_internet` TEXT NULL ,
  `meta_tags` TEXT NULL ,
  `revision` INT NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`asset_metadata_id`) ,
  INDEX `asset_meta_id` (`asset_root_id` ASC) ,
  INDEX `asset_meta_domain` (`domain_name_id` ASC) ,
  INDEX `asset_meta_sub_category` (`category_sub_id` ASC) ,
  CONSTRAINT `asset_meta_id`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `asset_meta_domain`
    FOREIGN KEY (`domain_name_id` )
    REFERENCES `tvo_web_repository`.`domain_name` (`domain_name_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `asset_meta_sub_category`
    FOREIGN KEY (`category_sub_id` )
    REFERENCES `tvo_web_repository`.`category_sub` (`category_sub_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`right_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`right_type` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`right_type` (
  `right_type_id` INT NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`right_type_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_right`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_right` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_right` (
  `asset_right_id` INT NOT NULL ,
  `asset_root_id` INT NULL ,
  `right_type_id` INT NULL ,
  `start_time` DATETIME NULL ,
  `end_time` DATETIME NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` DATETIME NULL ,
  `updated_by` DATETIME NULL ,
  PRIMARY KEY (`asset_right_id`) ,
  INDEX `fk_right_type` (`right_type_id` ASC) ,
  INDEX `fk_asset_rights` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_right_type`
    FOREIGN KEY (`right_type_id` )
    REFERENCES `tvo_web_repository`.`right_type` (`right_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_asset_rights`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`strands`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`strands` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`strands` (
  `strand_id` INT NOT NULL AUTO_INCREMENT ,
  `strand_title` VARCHAR(100) NULL ,
  `strand_description` TEXT NULL ,
  `listing_strand` CHAR(1) NULL ,
  `web_strand` CHAR(1) NULL ,
  `strand_active` CHAR(1) NULL ,
  `changed_by` VARCHAR(40) NULL ,
  `change_time` DATETIME NULL ,
  `strand_color` VARCHAR(6) NULL ,
  PRIMARY KEY (`strand_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`strands_schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`strands_schedule` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`strands_schedule` (
  `strands_schedule_id` INT NOT NULL AUTO_INCREMENT ,
  `strand_id` INT NULL ,
  `start_datetime` DATETIME NULL ,
  `end_datetime` DATETIME NULL ,
  `listings_strand_title` CHAR(1) NULL ,
  `listings_strand_description` CHAR(1) NULL ,
  `listings_series_description` CHAR(1) NULL ,
  `listings_program_title` CHAR(1) NULL ,
  `listings_program_description` CHAR(1) NULL ,
  `web_strand_title` CHAR(1) NULL ,
  `web_strand_description` CHAR(1) NULL ,
  `web_series_title` CHAR(1) NULL ,
  `web_series_description` CHAR(1) NULL ,
  `web_program_title` CHAR(1) NULL ,
  `web_program_description` CHAR(1) NULL ,
  `is_active` CHAR(1) NULL ,
  `change_by` VARCHAR(40) NULL ,
  `change_time` DATETIME NULL ,
  PRIMARY KEY (`strands_schedule_id`) ,
  INDEX `fk_strands` (`strands_schedule_id` ASC) ,
  CONSTRAINT `fk_strands`
    FOREIGN KEY (`strands_schedule_id` )
    REFERENCES `tvo_web_repository`.`strands` (`strand_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`air_time`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`air_time` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`air_time` (
  `air_time_id` INT NOT NULL ,
  `asset_root_id` INT NULL ,
  `strand_id` VARCHAR(45) NULL ,
  `air_time_type` INT NULL ,
  `air_time` DATETIME NULL ,
  `strands_schedule_id` INT NULL ,
  `is_repeat` BIT NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`air_time_id`) ,
  INDEX `fk_air_time_id` (`asset_root_id` ASC) ,
  INDEX `fk_strands_schedule_id` (`strands_schedule_id` ASC) ,
  CONSTRAINT `fk_air_time_id`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_strands_schedule_id`
    FOREIGN KEY (`strands_schedule_id` )
    REFERENCES `tvo_web_repository`.`strands_schedule` (`strands_schedule_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`air_time_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`air_time_type` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`air_time_type` (
  `air_time_type_id` INT NOT NULL AUTO_INCREMENT ,
  `type_name` VARCHAR(45) NULL ,
  `created_on` DATETIME NULL ,
  `updated_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`air_time_type_id`) ,
  INDEX `fk_airtime_type` (`air_time_type_id` ASC) ,
  CONSTRAINT `fk_airtime_type`
    FOREIGN KEY (`air_time_type_id` )
    REFERENCES `tvo_web_repository`.`air_time` (`air_time_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`asset_blog_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`asset_blog_post` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`asset_blog_post` (
  `asset_blog_post_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_blog_id` INT NULL ,
  `detail` TEXT NULL ,
  `sort_order` BIT NULL ,
  PRIMARY KEY (`asset_blog_post_id`) ,
  INDEX `fk_asset_blog_post` (`asset_blog_id` ASC) ,
  CONSTRAINT `fk_asset_blog_post`
    FOREIGN KEY (`asset_blog_id` )
    REFERENCES `tvo_web_repository`.`asset_blog` (`asset_blog_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`category_sub_asset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`category_sub_asset` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`category_sub_asset` (
  `category_sub_asset_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `category_sub_id` INT NULL ,
  `created_on` DATETIME NULL ,
  `update_on` TIMESTAMP NULL ,
  `created_by` VARCHAR(40) NULL ,
  `updated_by` VARCHAR(40) NULL ,
  PRIMARY KEY (`category_sub_asset_id`) ,
  INDEX `fk_category_sub` (`category_sub_id` ASC) ,
  INDEX `fk_category_asset_root` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_category_sub`
    FOREIGN KEY (`category_sub_id` )
    REFERENCES `tvo_web_repository`.`category_sub` (`category_sub_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_asset_root`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`contributor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`contributor` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`contributor` (
  `contributor_id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(60) NULL ,
  `unique_name` VARCHAR(80) NULL ,
  PRIMARY KEY (`contributor_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`contributor_profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`contributor_profile` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`contributor_profile` (
  `contributor_profile_id` INT NOT NULL AUTO_INCREMENT ,
  `bio` TEXT NULL ,
  `image` TEXT NULL ,
  `thumbnail_image` TEXT NULL ,
  `link` TEXT NULL ,
  `domain_name_id` INT NULL ,
  `revision` INT NULL ,
  `contributor_id` INT NULL ,
  `screen_name` VARCHAR(60) NULL ,
  PRIMARY KEY (`contributor_profile_id`) ,
  INDEX `fk_contributor_id` (`contributor_id` ASC) ,
  INDEX `fk_contributor_domain_id` (`domain_name_id` ASC) ,
  CONSTRAINT `fk_contributor_id`
    FOREIGN KEY (`contributor_id` )
    REFERENCES `tvo_web_repository`.`contributor` (`contributor_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_domain_id`
    FOREIGN KEY (`domain_name_id` )
    REFERENCES `tvo_web_repository`.`domain_name` (`domain_name_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`contributor_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`contributor_type` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`contributor_type` (
  `contributor_type_id` INT NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NULL ,
  PRIMARY KEY (`contributor_type_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`contributor_profile_article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`contributor_profile_article` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`contributor_profile_article` (
  `contributor_article_profile_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_article_id` INT NULL ,
  `contributor_profile_id` INT NULL ,
  `contributor_type_id` INT NULL ,
  PRIMARY KEY (`contributor_article_profile_id`) ,
  INDEX `fk_contributor_article_profile_id` (`contributor_profile_id` ASC) ,
  INDEX `fk_contributor_asset_article_id` (`asset_article_id` ASC) ,
  INDEX `fk_contributor_article_type_id` (`contributor_type_id` ASC) ,
  CONSTRAINT `fk_contributor_article_profile_id`
    FOREIGN KEY (`contributor_profile_id` )
    REFERENCES `tvo_web_repository`.`contributor_profile` (`contributor_profile_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_asset_article_id`
    FOREIGN KEY (`asset_article_id` )
    REFERENCES `tvo_web_repository`.`asset_article` (`asset_article_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_article_type_id`
    FOREIGN KEY (`contributor_type_id` )
    REFERENCES `tvo_web_repository`.`contributor_type` (`contributor_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`contributor_profile_blog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`contributor_profile_blog` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`contributor_profile_blog` (
  `contributor_profile_blog_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_blog_id` INT NULL ,
  `contributor_profile_id` INT NULL ,
  `contributor_type_id` INT NULL ,
  PRIMARY KEY (`contributor_profile_blog_id`) ,
  INDEX `fk_contributor_blog_profile_id` (`contributor_profile_blog_id` ASC) ,
  INDEX `fk_contributor_blog_asset_root_id` (`asset_blog_id` ASC) ,
  INDEX `fk_contributor_blog_type_id` (`contributor_type_id` ASC) ,
  CONSTRAINT `fk_contributor_blog_profile_id`
    FOREIGN KEY (`contributor_profile_blog_id` )
    REFERENCES `tvo_web_repository`.`contributor_profile` (`contributor_profile_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_blog_asset_root_id`
    FOREIGN KEY (`asset_blog_id` )
    REFERENCES `tvo_web_repository`.`asset_blog` (`asset_blog_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_blog_type_id`
    FOREIGN KEY (`contributor_type_id` )
    REFERENCES `tvo_web_repository`.`contributor_type` (`contributor_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`contributor_profile_blog_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`contributor_profile_blog_post` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`contributor_profile_blog_post` (
  `contributor_profile_blog_post_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_blog_post_id` INT NULL ,
  `contributor_profile_id` INT NULL ,
  `contributor_type_id` INT NULL ,
  PRIMARY KEY (`contributor_profile_blog_post_id`) ,
  INDEX `fk_contributor_blog_post_profile_id` (`contributor_profile_id` ASC) ,
  INDEX `fk_contributor_asset_blog_post_id` (`asset_blog_post_id` ASC) ,
  INDEX `fk_contributor_profile_blog_post_type` (`contributor_type_id` ASC) ,
  CONSTRAINT `fk_contributor_blog_post_profile_id`
    FOREIGN KEY (`contributor_profile_id` )
    REFERENCES `tvo_web_repository`.`contributor_profile` (`contributor_profile_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_asset_blog_post_id`
    FOREIGN KEY (`asset_blog_post_id` )
    REFERENCES `tvo_web_repository`.`asset_blog_post` (`asset_blog_post_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_profile_blog_post_type`
    FOREIGN KEY (`contributor_type_id` )
    REFERENCES `tvo_web_repository`.`contributor_type` (`contributor_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`contributor_profile_asset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`contributor_profile_asset` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`contributor_profile_asset` (
  `id_contributor_profile_asset` INT NOT NULL AUTO_INCREMENT ,
  `contributor_profile_id` INT NULL ,
  `contributor_type_id` INT NULL ,
  `asset_root_id` INT NULL ,
  PRIMARY KEY (`id_contributor_profile_asset`) ,
  INDEX `fk_contributor_asset_profile_id` (`id_contributor_profile_asset` ASC) ,
  INDEX `fk_contributor_asset_root_id` (`asset_root_id` ASC) ,
  INDEX `fk_contributor_asset_type` (`contributor_type_id` ASC) ,
  CONSTRAINT `fk_contributor_asset_profile_id`
    FOREIGN KEY (`id_contributor_profile_asset` )
    REFERENCES `tvo_web_repository`.`contributor_profile` (`contributor_profile_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_asset_root_id`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_asset_type`
    FOREIGN KEY (`contributor_type_id` )
    REFERENCES `tvo_web_repository`.`contributor_type` (`contributor_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tvo_web_repository`.`log_asset_telescope`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tvo_web_repository`.`log_asset_telescope` ;

CREATE  TABLE IF NOT EXISTS `tvo_web_repository`.`log_asset_telescope` (
  `asset_log_id` INT NOT NULL AUTO_INCREMENT ,
  `asset_root_id` INT NULL ,
  `action_command` VARCHAR(60) NULL ,
  `is_pass` BIT NULL ,
  `java_exception` TEXT NULL ,
  `warnings` TEXT NULL ,
  `created_on` TIMESTAMP NULL ,
  PRIMARY KEY (`asset_log_id`) ,
  INDEX `fk_log_asset_root_id` (`asset_root_id` ASC) ,
  CONSTRAINT `fk_log_asset_root_id`
    FOREIGN KEY (`asset_root_id` )
    REFERENCES `tvo_web_repository`.`asset_root` (`asset_root_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
