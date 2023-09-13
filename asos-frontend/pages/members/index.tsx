
interface MemberModel {
    id: string,
    alias: string,
    role: string,
    hasKey: boolean
}

interface AppProps {
    members: MemberModel[]
}

export default function Members(props: AppProps) {
    const { members } = props
    return(
    <ul>
        {members.map(member => (
            <li key={Math.floor(Math.random()*1000)}>{member.alias}</li>
        ))}
    </ul>
    );
}

export async function getServerSideProps() {
    let apiUrl: string = process.env.NEXT_PUBLIC_API_URL ?? '';
    apiUrl = new URL('members', apiUrl).href
    const res = await fetch(apiUrl);
    const members: MemberModel[] = await res.json();
    return {
        props: {
            members: members
        }
    }
}