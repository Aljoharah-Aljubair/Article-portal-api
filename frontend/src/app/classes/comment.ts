export class Comment {
  constructor(
    public id: number,
    public content: string,
    public userId: number,
    public username: string,
    public articleId: number,
    public createdAT: string) { };
}
