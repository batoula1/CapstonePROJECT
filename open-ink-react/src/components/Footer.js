import React from 'react'

function Footer() {
    return (
        <footer className='footer'>
            <div className="container-fluid">
                <div className="row">
                    <div className="col-lg-6">
                        <p className='foot-heading'>More About Webstite</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Impedit, provident! Ab quos provident excepturi amet, odit quibusdam odio ducimus laboriosam numquam nihil quaerat, reprehenderit ex, sed nesciunt sunt optio quidem? Suscipit laudantium maxime aut, commodi enim pariatur neque harum laborum id nisi voluptatem aliquid dolores omnis eligendi illo fugit quos!</p>
                    </div>
                    <div className="col-lg-3">
                        <p className='foot-heading'>Keep Connet</p>
                        <ul>
                            <li><a href="#">facebook</a></li>
                            <li><a href="#">Twitter</a></li>
                            <li><a href="#">Youtube</a></li>
                            <li><a href="#">Pinterest</a></li>
                        </ul>
                    </div>
                    <div className="col-lg-3">
                        <p className='foot-heading'>Contact Information</p>
                        <ul>
                            <li>
                                <a href="#">
                                    <i className="fa-white fas fa-home fa-sm"></i> Amazon rainforest
                                </a>
                            </li>
                            <li><a href="tel:+123456789"><i className="fa-white fas fa-phone"></i>+0123456789</a></li>
                            <li><a href="mailto:user@gmail.com"><i className="fa-white fas fa-envelope"></i> user@gmail.com</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div className="footer-ribon">
                <span>&copy; 2024 All Rights reserved by OpenInk</span>
            </div>
        </footer>
    )
}

export default Footer