/*
 Source Server Type    : PostgreSQL
 Source Catalog        : domain-architecture-sample
 Source Schema         : public
 Date: 12/11/2020 23:17:34
*/


-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS "public"."account";
CREATE TABLE "public"."account"
(
    "id"         int8                                       NOT NULL,
    "name"       varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
    "network_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
    "email"      varchar(128) COLLATE "pg_catalog"."default",
    "created_at" timestamp(6)                               NOT NULL DEFAULT now(),
    "updated_at" timestamp(6)                               NOT NULL DEFAULT now()
);

COMMENT ON COLUMN "public"."account"."id" IS 'primary key';
COMMENT ON COLUMN "public"."account"."name" IS 'user full name';
COMMENT ON COLUMN "public"."account"."network_id" IS 'user ikea network id';
COMMENT ON COLUMN "public"."account"."email" IS 'user email';
COMMENT ON COLUMN "public"."account"."created_at" IS 'user created time';
COMMENT ON COLUMN "public"."account"."updated_at" IS 'user latest updated time';
COMMENT ON TABLE "public"."account" IS 'user account info';

-- ----------------------------
-- Indexes structure for table account
-- ----------------------------
CREATE UNIQUE INDEX "uidx_account_network_id" ON "public"."account" USING btree (
                                                                                 "network_id"
                                                                                 COLLATE "pg_catalog"."default"
                                                                                 "pg_catalog"."text_ops" ASC NULLS LAST
    );

-- ----------------------------
-- Primary Key structure for table account
-- ----------------------------
ALTER TABLE "public"."account"
    ADD CONSTRAINT "account_pkey" PRIMARY KEY ("id");


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "public"."role";
CREATE TABLE "public"."role"
(
    "id"         int8                                       NOT NULL,
    "name"       varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
    "created_at" timestamp(6)                               NOT NULL DEFAULT now(),
    "updated_at" timestamp(6)                               NOT NULL DEFAULT now()
);

COMMENT ON COLUMN "public"."role"."id" IS 'primary key';
COMMENT ON COLUMN "public"."role"."name" IS 'role name';
COMMENT ON COLUMN "public"."role"."created_at" IS 'role created time';
COMMENT ON COLUMN "public"."role"."updated_at" IS 'role latest updated time';
COMMENT ON TABLE "public"."role" IS 'user role';

-- ----------------------------
-- Primary Key structure for table role
-- ----------------------------
ALTER TABLE "public"."role"
    ADD CONSTRAINT "role_pkey" PRIMARY KEY ("id");


DROP TABLE IF EXISTS "public"."r_account_role";
CREATE TABLE "public"."r_account_role"
(
    "id"         int8         NOT NULL,
    "account_id" int8         NOT NULL,
    "role_id"    int8         NOT NULL,
    "created_at" timestamp(6) NOT NULL
);

COMMENT ON COLUMN "public"."r_account_role"."id" IS 'primary key';
COMMENT ON COLUMN "public"."r_account_role"."account_id" IS 'refer to account id';
COMMENT ON COLUMN "public"."r_account_role"."role_id" IS 'refer to role id';
COMMENT ON COLUMN "public"."r_account_role"."created_at" IS 'relationship created time';
COMMENT ON TABLE "public"."r_account_role" IS 'relationship table for account and role';

-- ----------------------------
-- Primary Key structure for table r_account_role
-- ----------------------------
ALTER TABLE "public"."r_account_role"
    ADD CONSTRAINT "r_account_role_pkey" PRIMARY KEY ("id");
