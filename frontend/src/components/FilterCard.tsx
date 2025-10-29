import React from 'react'

interface FilterCardProps {
  title: string
  children: React.ReactNode
  onClear: () => void
  resultCount: number
  resultLabel: string
}

export function FilterCard({ title, children, onClear, resultCount, resultLabel }: FilterCardProps) {
  return (
    <>
      {/* Filtres */}
      <div className="card mb-4">
        <div className="card-header d-flex justify-content-between align-items-center">
          <h5 className="mb-0">{title}</h5>
          <button 
            className="btn btn-outline-secondary btn-sm" 
            onClick={onClear}
          >
            Effacer les filtres
          </button>
        </div>
        <div className="card-body">
          {children}
        </div>
      </div>

      {/* Résultats */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <span className="text-muted">{resultCount} {resultLabel} trouvé(s)</span>
      </div>
    </>
  )
}
