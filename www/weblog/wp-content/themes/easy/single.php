<?php

/* 	Easy Theme's Single Page to display Single Page or Post
	Copyright: 2013, D5 Creation, www.d5creation.com
	Based on the Simplest D5 Framework for WordPress
	Since Easy 1.0
*/


get_header(); ?><div id="container">  

<div id="content">
          
		  <?php if ( have_posts() ) while ( have_posts() ) : the_post(); ?> 
            <h1 class="page-title"><?php the_title(); ?></h1>
            <p class="postmetadataw">Posted by: <?php the_author_posts_link() ?> | on <?php the_time('F j, Y'); ?></p> 
                        
            <div class="content-ver-sep"> </div>
            <div class="entrytext"><?php the_post_thumbnail('category-thumb'); ?>
			<?php the_content(); ?>
            
            <div class="clear"> </div>
            <div class="up-bottom-border">
            <p class="postmetadata">Posted in <?php the_category(', ') ?> | <?php edit_post_link('Edit', '', ' | '); ?>  <?php comments_popup_link('No Comments' . ' &#187;', 'One Comment' . '  &#187;', '% ' . 'Comments' . ' &#187;'); ?> <?php the_tags('<br />' .  'Tags' . ': ', ', ', '<br />'); ?></p>
            <?php  wp_link_pages( array( 'before' => '<div class="page-link"><span>' . 'Pages:' . '</span>', 'after' => '</div>' ) ); ?>
            <div class="content-ver-sep"> </div>
            <div id="page-nav">
            <div class="alignleft"><?php previous_post_link('%link (' . 'Previous Post' . ')'); ?></div>
            <div class="alignright"><?php next_post_link('(' . 'Next Post' . ') %link'); ?></div>
            <div class="clear"> </div>
            <div class="alignleft"><?php previous_image_link( false, 'Previous Image' ); ?></div>
			<div class="alignright"><?php next_image_link( false,  'Next Image' ); ?></div> 
          	</div>
            </div></div>
			
			<?php endwhile;?>
          	            
          <!-- End the Loop. -->          
        	
			<?php comments_template(); ?>
            
</div>			
<?php get_sidebar(); ?>
<?php get_footer(); ?>