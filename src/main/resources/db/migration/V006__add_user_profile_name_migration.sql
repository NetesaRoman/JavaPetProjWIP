ALTER TABLE vote_site.user ADD profile_name text;

UPDATE vote_site.user
SET profile_name = name;







