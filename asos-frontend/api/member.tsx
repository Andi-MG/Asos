import { Member, NewMember } from "model/Member";
import { NextApiRequest, NextApiResponse } from "next";

function handler(req: NextApiRequest, res: NextApiResponse) {
    if(req.method === 'POST'){
        const alias: string = req.body.alias;
        const dni: string = req.body.dni;
        const hasKey: boolean = req.body.hasKey;
        
        // Validate, handle errors
        // Notify
        // post it to backend
        const response: MembersResponse = addNewMember({alias:alias, dni:dni});
        // handle possible errors
        if (response.error){
            res.status(401);
            // notify error
        }
        res.status(200).json(response.member);
        // notify success
    }
    else {
        res.status(405);
    }
}

function addNewMember(newMember: NewMember):MembersResponse{
    let apiUrl: string = process.env.NEXT_PUBLIC_API_URL ?? '';
    apiUrl = new URL('members', apiUrl).href
    const res = fetch(apiUrl, {headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: "POST", body: JSON.stringify(newMember)});
    
    // PLACEHOLDER, replace
    return {member:{
        id:"jlal",
        alias:"Borja",
        dni:"324213"
    }, error:false}
}

interface MembersResponse {
    member: Member,
    error: boolean
}
export default handler;