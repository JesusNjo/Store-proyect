export interface Client{
    id:number,
    name:string,
    lastName:string,
    email:string,
    gender: Gender,
    address:string,
    password:string
}

interface Gender{
    MALE:"male",
    FEMALE:"female",
    OTHER:"other"
}