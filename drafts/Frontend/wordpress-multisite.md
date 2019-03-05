## Prerequisite
xampp, PHP > 7.3, Mysql > 5.6
Download Wordpress 5.1

Copy folder under htdocs. Open in browser and follow the instructions

Create multi site:
> No plugins should be activated
> Go to server and open wp-config.php and allow multisite
define( 'WP_DEBUG', false );

/** Multisite Ashish **/
define( 'WP_ALLOW_MULTISITE', true);
