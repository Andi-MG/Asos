import { Fragment } from "react";

function NewMemberForm () {
    return(<Fragment>
        <div>
            <label>Alias</label>
            <input type='text' id='alias'/>
        </div>
        <div>
            <label>DNI</label>
            <input type='text' id='dni'/>
        </div>
        </Fragment>
        );
}
export default NewMemberForm;