import React, { useContext, useEffect } from 'react';

import NewFeedInfinite from '../components/NewFeedInfinite';
import Base from '../components/Base';

const Home = () => {
    return (
        <Base>
            <div className='container mt-3'>
                <div className="row">
                    <div className="col-lg-2"></div>
                    <div className="col-lg-7">
                        {/* <NewFeed></NewFeed> */}
                        <NewFeedInfinite></NewFeedInfinite>
                    </div>
                    <div className="col-lg-3"></div>
                </div>
            </div>
        </Base>
    );
}

export default Home;
