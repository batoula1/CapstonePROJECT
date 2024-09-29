import React, { useEffect, useState } from 'react';
import { NavLink as ReactNavLink } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

import Logo from "../assets/images/logo.png";
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem
} from 'reactstrap';

import { doLogout, getCurrentUserInfo, isUserLoggedIn } from '../services/auth';
import SearchBar from './SearchBar';
import { getAllCategories } from '../services/categoryServices';

function CustomNavbar() {

    const navigate = useNavigate();

    const [isOpen, setIsOpen] = useState();
    const toggle = () => setIsOpen(!isOpen);


    const [isLoggedin, setLoggedIn] = useState(false);
    const [user, setUser] = useState(null);

    const [categories, setCategories] = useState([]); // State for categories


    useEffect(() => {
        setLoggedIn(isUserLoggedIn());
        if (isUserLoggedIn()) {
            setUser(getCurrentUserInfo());
        }
    }, [isLoggedin])


    const logout = () => {
        setLoggedIn(false);        
        doLogout()
            .then(() => {
                navigate("/home");
            });
    };

    useEffect(() => {
        getAllCategories()
            .then((data) => {
                setCategories(data);  // Set categories from API
            })
            .catch((err) => {
                console.log(err);
            });
    }, []);

    const onCategoryChange =(catName)=>{
        // alert(catName)
    }


    return (
        <div>
            <Navbar color='dark' dark={true} expand="md">

                <NavbarBrand tag={ReactNavLink} to="/home"><img src={Logo} id='logo' alt="Logo" />OpenInk</NavbarBrand>

                <NavbarToggler onClick={toggle} />


                <Collapse isOpen={isOpen} navbar>
                    <Nav className="me-auto" navbar>
                        <NavItem>
                            <NavLink tag={ReactNavLink} to='/home' >Home</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={ReactNavLink} to='/about' >About Us</NavLink>
                        </NavItem>
                        <UncontrolledDropdown nav inNavbar>
                            <DropdownToggle nav caret>
                                Category
                            </DropdownToggle>
                            <DropdownMenu end>
                                {categories.map((category) => (
                                    <DropdownItem
                                        key={category.categoryId}
                                        onClick={() => onCategoryChange(category.categoryTitle)} // Pass selected category
                                    >
                                        {category.categoryTitle}
                                    </DropdownItem>
                                ))}
                            </DropdownMenu>

                            
                            
                        </UncontrolledDropdown>
                    </Nav>
                    <Nav className='mx-lg-auto w-50'>
                        <SearchBar></SearchBar>
                    </Nav>

                    <Nav navbar>

                        {isLoggedin && (
                            <>
                                <NavItem>
                                    <NavLink tag={ReactNavLink} to='/user/dashboard' >{user.name} </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink tag={ReactNavLink} onClick={() => logout()}>Logout </NavLink>
                                </NavItem>
                            </>
                        )}

                        {
                            !isLoggedin && (
                                <>
                                    <NavItem>
                                        <NavLink tag={ReactNavLink} to='/login' >Login </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactNavLink} to='/signup' >Sign Up</NavLink>
                                    </NavItem>
                                </>
                            )
                        }

                    </Nav>

                </Collapse>
            </Navbar>
        </div>
    );
}

export default CustomNavbar;