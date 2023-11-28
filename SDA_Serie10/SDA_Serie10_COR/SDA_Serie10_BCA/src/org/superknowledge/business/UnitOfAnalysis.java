package org.superknowledge.business;

import java.util.List;
import java.util.Objects;

public abstract class UnitOfAnalysis {

    /**
     * Unique id of the document.
     * Can come from a database.
     */
    protected int id;

    /** Zero-based unique id. Range [0 to n].
     * This id is generated to be compatible with VOS viewer {@link cwts.networkanalysis.Network}.
     * This id can change among the document or reference lifecycle.
     * For example, this id is generated at each new performed analysis.
     * @see {https://en.wikipedia.org/wiki/Zero-based_numbering}
     */
    protected int zeroBasedId;

    protected BibliometricMetric bibliometricMetric;
    protected boolean retainedInAnalyis;

    public UnitOfAnalysis(){
        id = -1;
        zeroBasedId = -1;
        bibliometricMetric = new BibliometricMetric();
        retainedInAnalyis = false;
    }

    public static int[] getZeroBasedId(List<? extends UnitOfAnalysis> unitOfAnalyses){
        int[] referencesId = new int[unitOfAnalyses.size()];
        for(int i = 0; i < unitOfAnalyses.size(); i++) {
            referencesId[i] = unitOfAnalyses.get(i).getZeroBasedId();
        }
        return referencesId;
    }

    public abstract String getLabel();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZeroBasedId() {
        return zeroBasedId;
    }

    public void setZeroBasedId(int zeroBasedId) {
        this.zeroBasedId = zeroBasedId;
    }

    public BibliometricMetric getBibliometricMetric() {
        return bibliometricMetric;
    }

    public void setBibliometricMetric(BibliometricMetric bibliometricMetric) {
        this.bibliometricMetric = bibliometricMetric;
    }

    public boolean isRetainedInAnalyis() {
        return retainedInAnalyis;
    }

    public void setRetainedInAnalyis(boolean retainedInAnalyis) {
        this.retainedInAnalyis = retainedInAnalyis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitOfAnalysis)) return false;
        UnitOfAnalysis that = (UnitOfAnalysis) o;
        return id == that.id &&
                zeroBasedId == that.zeroBasedId &&
                retainedInAnalyis == that.retainedInAnalyis &&
                bibliometricMetric.equals(that.bibliometricMetric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zeroBasedId, bibliometricMetric, retainedInAnalyis);
    }

}
