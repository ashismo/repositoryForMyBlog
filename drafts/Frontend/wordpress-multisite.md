## Prerequisite
xampp, PHP > 7.3, Mysql > 5.6
Download Wordpress 5.1

Copy folder under htdocs. Open in browser and follow the instructions

Create multi site:
> No plugins should be activated
> Go to server and open wp-config.php and allow multisite
define( 'WP_DEBUG', false );

/** Database change **/
define( 'DB_NAME', 'XXXXXX' );

/** MySQL database username */
define( 'DB_USER', 'XXXXXX' );

/** MySQL database password */
define( 'DB_PASSWORD', 'XXXXXX' );

/** MySQL hostname ===== This is important to mention the port number*/ 
define( 'DB_HOST', '127.0.0.1:3306' );


/** Multisite Ashish **/
define( 'WP_ALLOW_MULTISITE', true);

Go to Tools -> Network Setup then click Install then follow the instruction

define( 'WP_DEBUG', false );

/** Multisite Ashish **/
define( 'WP_ALLOW_MULTISITE', true);

define('MULTISITE', true);
define('SUBDOMAIN_INSTALL', false);
define('DOMAIN_CURRENT_SITE', '3.17.188.198');
define('PATH_CURRENT_SITE', '/wordpress/');
define('SITE_ID_CURRENT_SITE', 1);
define('BLOG_ID_CURRENT_SITE', 1);

/* That's all, stop editing! Happy publishing. */



Create a file .htaccess under /opt/lampp/htdocs/wordpress and paste the code suggested in the installation page

<IfModule mod_rewrite.c>
RewriteEngine On
RewriteBase /wordpress/
RewriteRule ^index\.php$ - [L]

# add a trailing slash to /wp-admin
RewriteRule ^([_0-9a-zA-Z-]+/)?wp-admin$ $1wp-admin/ [R=301,L]

RewriteCond %{REQUEST_FILENAME} -f [OR]
RewriteCond %{REQUEST_FILENAME} -d
RewriteRule ^ - [L]
RewriteRule ^([_0-9a-zA-Z-]+/)?(wp-(content|admin|includes).*) $2 [L]
RewriteRule ^([_0-9a-zA-Z-]+/)?(.*\.php)$ $2 [L]
RewriteRule . index.php [L]

</IfModule>




## Additional information
Create web application inside webpress - Install Caspio plugin