import Button from "./Button";
import { AiOutlinePlus } from "react-icons/ai";

function ProfileCard({ title, handle, image, description }){




    return (
       <div className="card">
            <div className="card-image">
                <figure className="image is-1by1 shadow-indigo-500/40">
                    <img src={image} alt='pda logo' />
                </figure>
            </div>
       
            <div className="card-content">
                <div className="media-content">
                    <p className="title is-4">{title}</p>
                    <p className="subtitle is-6">$ {handle}</p>
                 
                </div>
                <div className="truncate owerflow:hidden">{description}</div>
            </div>
            <div className="flex justify-center bm-100">
            </div>
       </div> 

    );

}
export default ProfileCard;