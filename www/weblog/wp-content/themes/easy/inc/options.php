<?php
/**
 * 	easy Options Page
 * 	@Copyright: D5 Creation, All Rights, www.d5creation.com
 * 	Description: A framework for building theme options.
	Author: Devin Price
	Author URI: http://www.wptheming.com
	License: GPLv2
	Version: 1.3
 */

function optionsframework_option_name() {

	// This gets the theme name from the stylesheet
	$themename = 'easy';
	$optionsframework_settings = get_option( 'optionsframework' );
	$optionsframework_settings['id'] = $themename;
	update_option( 'optionsframework', $optionsframework_settings );
}

/**
 * Defines an array of options that will be used to generate the settings page and be saved in the database.
 * When creating the 'id' fields, make sure to use all lowercase and no spaces.
 *
 * If you are making your theme translatable, you should replace 'easy'
 * with the actual text domain for your theme.  Read more:
 * http://codex.wordpress.org/Function_Reference/load_theme_textdomain
 */


function optionsframework_options() {

	$options[] = array(
		'name' => 'Easy Options',
		'type' => 'heading');
		
	$options[] = array(
		'desc' => '<div class="infohead"><span class="donation">If you like this FREEE Theme You can consider for a small Donation to us. Your Donation will be spent for the Disadvantaged Children and Students. You can visit our <a href="http://d5creation.com/donate/" target="_blank"><strong>DONATION PAGE</strong></a> and Take your decision.</span><br /><br /><span class="donation"> Need More Features and Options including Exciting Slide Effects with more Slides from Pages or Posts and 100+ Advanced Features? Try <a href="http://d5creation.com/theme/easy/" target="_blank"><strong>Easy Extend</strong></a>.</span><br /> <br /><span class="donation"> You can Visit the Easy Extend Demo <a href="http://demo.d5creation.com/wp/themes/easy/" target="_blank"><strong>Here</strong></a>.</span><a href="http://d5creation.com/theme/kabbo/" target="_blank" class="extendlink"> </a></div>',
		'type' => 'info');	
	
	
	$options[] = array(
		'name' => 'Front Page Heading',
		'desc' => 'Input your heading text here. Plese limit it within 100 Letters. You can also input any HTML, Videos or iframe here. ',
		'id' => 'heading_text',
		'std' => 'WordPress is web software you can use to create a <a href="#">beautiful website or blog</a>. We like to say that <a href="http://wordpress.org/">WordPress</a> is both free and priceless at the same time',
		'type' => 'text' );
	
	$options[] = array(
		'name' => 'Google Plus Link',
		'desc' => 'Input your You Tube Channel URL here.',
		'id' => 'gplus-link',
		'std' => 'http://www.plus.google.com/d5creation',
		'type' => 'text');

	$options[] = array(
		'name' => 'Linked In Link',
		'desc' => 'Input your Linked In URL here.',
		'id' => 'li-link',
		'std' => 'http://lnkd.in/waY9_A',
		'type' => 'text');

	$options[] = array(
		'name' => 'Feed or Blog Link',
		'desc' => 'Input your Feed or Blog URL here.',
		'id' => 'feed-link',
		'std' => 'http://d5creation.com/news/',
		'type' => 'text');	


	$options[] = array(
		'name' => 'Quote Text',
		'desc' => 'Input your Front Page Bottom Quotation here. Plese limit it within 150 Letters.',
		'id' => 'bottom-quotation',
		'std' => 'All the developers of D5 Creation have come from the disadvantaged part or group of the society. All have established themselves after a long and hard struggle in their life ----- D5 Creation Team',
		'type' => 'textarea');
		
	
	return $options;
}

/*
 * This is an example of how to add custom scripts to the options panel.
 * This example shows/hides an option when a checkbox is clicked.
 */

add_action('optionsframework_custom_scripts', 'optionsframework_custom_scripts');

function optionsframework_custom_scripts() { ?>

<?php
}