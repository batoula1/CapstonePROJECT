import React from 'react';
import Base from '../components/Base';
import logo from '../assets/images/logo.png';

function About() {
    return (
        <Base>
            <div className='container'>
                <div className='text-center'>
                    <img src={logo} className='img-fluid' alt='OpenInk Logo' />
                </div>
                <h1 className='text-center my-4'>About OpenInk</h1>
                <p>
                    Welcome to <strong>OpenInk</strong>, your go-to platform for expressing thoughts, sharing stories, and connecting
                    with a community of like-minded individuals. Built with modern technology, OpenInk ensures a seamless experience
                    for bloggers, writers, and readers alike.
                </p>
                <p>
                    At <strong>OpenInk</strong>, we believe in the power of words. Whether you're a seasoned writer or someone looking to
                    share your thoughts for the first time, our platform offers a variety of features to make your blogging experience
                    enjoyable and engaging.
                </p>
                <h2>What We Offer</h2>
                <ul>
                    <li><strong>User Authentication:</strong> Secure login and signup system using JWT and Spring Security to keep your data safe.</li>
                    <li><strong>User Dashboard:</strong> Manage your blogs, comments, and account settings from one convenient place.</li>
                    <li><strong>Blog View Page:</strong> Dive into insightful posts on various topics.</li>
                    <li><strong>Comments:</strong> Share your thoughts and engage with other users.</li>
                    <li><strong>Infinite Scroll Newsfeed:</strong> Enjoy smooth browsing with our endless newsfeed.</li>
                    <li><strong>Search Functionality:</strong> Find blogs, topics, or writers with ease using advanced search options.</li>
                </ul>
                <h2>Our Technology</h2>
                <p>
                    <strong>OpenInk</strong> is powered by cutting-edge technologies to ensure the best experience:
                </p>
                <ul>
                    <li><strong>Backend:</strong> Java Spring Boot with MySQL and Spring JPA for efficient data management.</li>
                    <li><strong>Security:</strong> Secured with Spring Security and JWT authentication.</li>
                    <li><strong>Frontend:</strong> Built using React JS and styled with Bootstrap for a modern, responsive interface.</li>
                </ul>
                <p>
                    At <strong>OpenInk</strong>, we are committed to offering a platform that encourages creativity and connection. Whether you're here
                    to write, read, or simply explore, we hope OpenInk becomes your favorite space for thoughtful content.
                </p>
                <p><strong>Happy writing!</strong></p>
            </div>
        </Base>
    );
}

export default About;
