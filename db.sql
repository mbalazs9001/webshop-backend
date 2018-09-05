--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: codecoolshop; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE codecoolshop WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Hungarian_Hungary.1250' LC_CTYPE = 'Hungarian_Hungary.1250';


ALTER DATABASE codecoolshop OWNER TO postgres;

\connect codecoolshop

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: addresses; Type: TABLE; Schema: public; Owner: danielcs88
--

CREATE TABLE public.addresses (
    id integer NOT NULL,
    zip_code character varying,
    country character varying,
    city character varying,
    street character varying,
    user_id integer NOT NULL
);


ALTER TABLE public.addresses OWNER TO danielcs88;

--
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: danielcs88
--

CREATE SEQUENCE public.addresses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.addresses_id_seq OWNER TO danielcs88;

--
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: danielcs88
--

ALTER SEQUENCE public.addresses_id_seq OWNED BY public.addresses.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: danielcs88
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    payment_id bigint,
    user_id integer,
    status character varying DEFAULT 'NEW'::character varying NOT NULL,
    date character varying
);


ALTER TABLE public.orders OWNER TO danielcs88;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: danielcs88
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO danielcs88;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: danielcs88
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: product_category; Type: TABLE; Schema: public; Owner: danielcs88
--

CREATE TABLE public.product_category (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    department character varying NOT NULL
);


ALTER TABLE public.product_category OWNER TO danielcs88;

--
-- Name: product_orders; Type: TABLE; Schema: public; Owner: danielcs88
--

CREATE TABLE public.product_orders (
    id integer NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.product_orders OWNER TO danielcs88;

--
-- Name: product_orders_id_seq; Type: SEQUENCE; Schema: public; Owner: danielcs88
--

CREATE SEQUENCE public.product_orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_orders_id_seq OWNER TO danielcs88;

--
-- Name: product_orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: danielcs88
--

ALTER SEQUENCE public.product_orders_id_seq OWNED BY public.product_orders.id;


--
-- Name: productcategory_id_seq; Type: SEQUENCE; Schema: public; Owner: danielcs88
--

CREATE SEQUENCE public.productcategory_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productcategory_id_seq OWNER TO danielcs88;

--
-- Name: productcategory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: danielcs88
--

ALTER SEQUENCE public.productcategory_id_seq OWNED BY public.product_category.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: danielcs88
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    price double precision NOT NULL,
    currency character varying NOT NULL,
    product_category_id integer,
    supplier_id integer,
    image character varying
);


ALTER TABLE public.products OWNER TO danielcs88;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: danielcs88
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO danielcs88;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: danielcs88
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: danielcs88
--

CREATE TABLE public.suppliers (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.suppliers OWNER TO danielcs88;

--
-- Name: suppliers_id_seq; Type: SEQUENCE; Schema: public; Owner: danielcs88
--

CREATE SEQUENCE public.suppliers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suppliers_id_seq OWNER TO danielcs88;

--
-- Name: suppliers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: danielcs88
--

ALTER SEQUENCE public.suppliers_id_seq OWNED BY public.suppliers.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: danielcs88
--

CREATE TABLE public.users (
    email character varying,
    phone character varying,
    name character varying NOT NULL,
    password character varying,
    id integer NOT NULL
);


ALTER TABLE public.users OWNER TO danielcs88;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: danielcs88
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO danielcs88;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: danielcs88
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: addresses id; Type: DEFAULT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.addresses ALTER COLUMN id SET DEFAULT nextval('public.addresses_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: product_category id; Type: DEFAULT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.product_category ALTER COLUMN id SET DEFAULT nextval('public.productcategory_id_seq'::regclass);


--
-- Name: product_orders id; Type: DEFAULT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.product_orders ALTER COLUMN id SET DEFAULT nextval('public.product_orders_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: suppliers id; Type: DEFAULT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.suppliers ALTER COLUMN id SET DEFAULT nextval('public.suppliers_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: danielcs88
--

COPY public.addresses (id, zip_code, country, city, street, user_id) FROM stdin;
2	1060	Hungary	Budapest	Nagymező street 42.	1
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: danielcs88
--

COPY public.orders (id, payment_id, user_id, status, date) FROM stdin;
2	987654321	1	NEW	\N
1	123456789	1	NEW	\N
\.


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: danielcs88
--

COPY public.product_category (id, name, description, department) FROM stdin;
1	Smarthphone	Phones that think instead of you.	Electronics
\.


--
-- Data for Name: product_orders; Type: TABLE DATA; Schema: public; Owner: danielcs88
--

COPY public.product_orders (id, order_id, product_id, quantity) FROM stdin;
1	1	1	3
2	2	1	7
4	1	1	7
5	1	1	10
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: danielcs88
--

COPY public.products (id, name, description, price, currency, product_category_id, supplier_id, image) FROM stdin;
1	Phone	A phone.	500	USD	1	1	asd
\.


--
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: danielcs88
--

COPY public.suppliers (id, name, description) FROM stdin;
1	Samsung	Cutting edge electronics.
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: danielcs88
--

COPY public.users (email, phone, name, password, id) FROM stdin;
admin@admin.org	123456789	John Doe	admin	1
\.


--
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: danielcs88
--

SELECT pg_catalog.setval('public.addresses_id_seq', 5, true);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: danielcs88
--

SELECT pg_catalog.setval('public.orders_id_seq', 3, true);


--
-- Name: product_orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: danielcs88
--

SELECT pg_catalog.setval('public.product_orders_id_seq', 5, true);


--
-- Name: productcategory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: danielcs88
--

SELECT pg_catalog.setval('public.productcategory_id_seq', 1, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: danielcs88
--

SELECT pg_catalog.setval('public.products_id_seq', 3, true);


--
-- Name: suppliers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: danielcs88
--

SELECT pg_catalog.setval('public.suppliers_id_seq', 1, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: danielcs88
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: product_orders product_orders_pkey; Type: CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.product_orders
    ADD CONSTRAINT product_orders_pkey PRIMARY KEY (id);


--
-- Name: product_category productcategory_pkey; Type: CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT productcategory_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: suppliers suppliers_pkey; Type: CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.suppliers
    ADD CONSTRAINT suppliers_pkey PRIMARY KEY (id);


--
-- Name: users users_id_pk; Type: CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_pk PRIMARY KEY (id);


--
-- Name: addresses addresses_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: orders orders_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product_orders product_orders_orders_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.product_orders
    ADD CONSTRAINT product_orders_orders_id_fk FOREIGN KEY (order_id) REFERENCES public.orders(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product_orders product_orders_products_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.product_orders
    ADD CONSTRAINT product_orders_products_id_fk FOREIGN KEY (product_id) REFERENCES public.products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: products products_product_category_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_product_category_id_fk FOREIGN KEY (product_category_id) REFERENCES public.product_category(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: products products_suppliers_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: danielcs88
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_suppliers_id_fk FOREIGN KEY (supplier_id) REFERENCES public.suppliers(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- PostgreSQL database dump complete
--

