import React, { useEffect, useState } from 'react'
import Base from '../../components/Base'
import { getCurrentUserInfo } from '../../services/auth'
import { Link, useLocation, useParams } from 'react-router-dom';
// import { getBlogsByUserId } from '../../services/blogServices';
import { BASE_URL } from '../../utils/constants';
import { getUserById } from '../../services/userServices';


function UserDashboard() {

    const [user, setUser] = useState([]);
    // const [userBlogs, setUserBlogs] = useState([]);
    const [myAccount, setMyAccount] = useState(false);

    /** VVI */
    //from request param
    const { userId } = useParams();
    const location = useLocation();

    useEffect(() => {
        const fetchData = async () => {
            const userInfo = getCurrentUserInfo();

            let userIdInt = Number.parseInt(userId)

            if (userId) {

                // *** ONEK IMPORTANT (Feedback given at last moment of the project)
                const fetchedUser = await getUserById(userIdInt)
                    .then(data => setUser(data))
                    .catch((err) => setUser([]))
                // setUser(fetchedUser);

                // //first a user Data asbe and ONLY THEN getBlogsByUserId() eta jeno kaj kore. Noile ID pabo na user er
                // const blogs = await getBlogsByUserId(userIdInt);
                // setUserBlogs(blogs);

            } else {
                setUser(userInfo);
                // if (userInfo && userInfo.id) {
                //     const blogs = await getBlogsByUserId(userInfo.id);
                //     setUserBlogs(blogs);
                // }
            }

            if (userInfo.id === userIdInt) {
                setMyAccount(true)
            } else {
                setMyAccount(false)
            }

        };

        fetchData();
    }, [userId, location.pathname]);


    if (user.length == 0) {
        return (<Base>
            <div className='display-3 text-center text-danger'>User Not Found</div>
        </Base>)
    }
    return (
        <Base>
            <div className="user-page">
                <div className="container">
                    {/* user page info */}
                    <div className="user-page-info">
                        <div className="row">
                            <div className="col-lg-3 pe-0 d-flex justify-content-center align-items-center"><img src={BASE_URL + "/UserImages/" + user.image} className='mw-100' alt="User" /></div>
                            <div className="col-lg-9">
                                <table className="mb-0 table  table-striped">
                                    <tbody>
                                        <tr>
                                            <th>User Id</th>
                                            <td>{user.id}</td>
                                        </tr>
                                        <tr>
                                            <th>Name</th>
                                            <td>{user.name}</td>
                                        </tr>
                                        <tr>
                                            <th>Email</th>
                                            <td>{user.email}</td>
                                        </tr>
                                        <tr>
                                            <th>About Me</th>
                                            <td>{user.about}</td>
                                        </tr>
                                        <tr>
                                            <th>Roles</th>
                                            <td>{user.roles && user.roles.map((role) => role.role + " ")}</td>
                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>


                    

                </div>
            </div>
        </Base>
    )
}

export default UserDashboard