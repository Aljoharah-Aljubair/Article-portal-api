// src/app/models/paginated-article-response.model.ts
import { Article } from './article';
export class PaginatedArticleResponse {
    content!: Article[];
    totalElements!: number;
  }